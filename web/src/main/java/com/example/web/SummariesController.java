package com.example.web;

import com.example.web.messaging.Post;
import com.example.web.messaging.PostSender;
import com.example.web.persistence.PostDocument;
import com.example.web.persistence.SummaryRepository;
import com.example.web.processing.PostSummaryCreator;
import com.example.web.requests.PostReqBody;
import com.example.web.requests.PostReqBodyValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class SummariesController {
    private final static Logger logger = LoggerFactory.getLogger(SummariesController.class);

    @Autowired
    private PostSender postSender;

    @Autowired
    private SummaryRepository summaryRepository;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new PostReqBodyValidator());
    }

    @GetMapping(value = "/summaries", produces = "application/json")
    public List<String> getSummaries() {
        return summaryRepository.findSummaryAndExcludeId().stream().map(PostDocument::getSummary).collect(Collectors.toList());
    }

    @PostMapping(value = "/summaries", consumes = "application/json")
    public ResponseEntity postSummary(@Valid @RequestBody PostReqBody postReqBody) {
        Post post = PostSummaryCreator.create(postReqBody.getText(), Integer.parseInt(postReqBody.getLength()));
        postSender.sendPost(post);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 400);
        response.put("message", "Validation error. Check 'details' to resolve");
        Map<String, String> details = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            details.put(fieldName, errorMessage);
        });
        response.put("details", details);
        return response;
    }
}

package com.beta.replyservice.controller;

import com.beta.replyservice.model.ErrorMessage;
import com.beta.replyservice.model.Message;
import com.beta.replyservice.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ReplyController {

	private final ReplyService replyService;

	public ReplyController(ReplyService replyService) {
		this.replyService = replyService;
	}

	@GetMapping("/reply")
	public ResponseEntity<Message> replying() {
		return buildResponse(this.replyService.processMessageV1(""));
	}

	@GetMapping("/reply/{message}")
	public ResponseEntity<Message> replying(@PathVariable String message) {
		return buildResponse(this.replyService.processMessageV1(message));
	}

	@GetMapping("/v2/reply")
	public ResponseEntity<Message> replyingV2() {
		return buildResponse(this.replyService.processMessageV2(""));
	}

	@GetMapping("/v2/reply/{message}")
	public ResponseEntity<Message> replyingV2(@PathVariable String message) {
		return buildResponse(this.replyService.processMessageV2(message));
	}

	private ResponseEntity<Message> buildResponse(Message message) {
		return new ResponseEntity<>(message, message instanceof ErrorMessage ? ((ErrorMessage) message).getStatus() : HttpStatus.OK);
	}
}
package com.ravenschool.web_example_1.rest;

import com.ravenschool.web_example_1.Constants.IEazySchoolConstants;
import com.ravenschool.web_example_1.Model.Contact;
import com.ravenschool.web_example_1.Model.Response;
import com.ravenschool.web_example_1.Repository.IContactRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/contact")
@AllArgsConstructor
public class ContactRestController {

    private final IContactRepository _IContactRepository;

    @GetMapping("/getMessageByStatus")
    public List<Contact> getMessageByStatus(@RequestParam(name = "status") String status) {
        return _IContactRepository.findByStatus(status);
    }

    @GetMapping("/getAllMsgsByStatus")
    public List<Contact> getAllMsgsByStatus(@RequestBody Contact contact) {
        if (contact != null && contact.getStatus() != null) {
            return _IContactRepository.findByStatus(contact.getStatus());
        } else
            return List.of();
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<Response> createMsg(@RequestHeader("invocationFrom") String invocationForm,
                                              @Valid @RequestBody Contact contact) {
        log.info(String.format("Header invocationFrom = %s", invocationForm));
        _IContactRepository.save(contact);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message saved successfully");

        HttpStatus status = HttpStatus.CREATED;
        HttpHeaders headers = new HttpHeaders();
        headers.add("isMsgSaved", "true");

        return new ResponseEntity<>(response, headers, status);
    }

    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach((key, value) -> {
            log.info(String.format(
                    "Header '%s' = %s", key, String.join("|", value)
            ));
        });

        Contact contact = requestEntity.getBody();
        _IContactRepository.deleteById(contact.getContactId());
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message successfully deleted");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/closeMsg")
    public ResponseEntity<Response> closeMsg(@RequestBody Contact requestContact) {
        Response response = new Response();
        Optional<Contact> contact = _IContactRepository.findById(requestContact.getContactId());
        if (contact.isPresent()) {
            contact.get().setStatus(IEazySchoolConstants.CLOSE);
            _IContactRepository.save(contact.get());
        } else {
            response.setStatusCode("400");
            response.setStatusCode("Invalid Contact ID received");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.setStatusCode("200");
        response.setStatusMsg("Message successfully closed");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

package br.com.event.management.system.application.events.partners;

import br.com.event.management.system.core.events.application.dto.RegisterPartnerInput;
import br.com.event.management.system.core.events.application.service.PartnerService;
import br.com.event.management.system.core.events.domain.entities.Partner;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/partners")
public class PartnersController {

  private final PartnerService partnerService;

  @GetMapping
  public ResponseEntity<List<Partner>> list() {
    final var response = this.partnerService.findAll();
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<Partner> create(@RequestBody final RegisterPartnerInput input) {
    final var response = this.partnerService.register(input);
    return ResponseEntity.ok(response);
  }

}

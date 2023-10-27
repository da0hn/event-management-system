package br.com.event.management.system.application.events.partners;

import br.com.event.management.system.core.events.application.dto.RegisterPartnerInput;
import br.com.event.management.system.core.events.application.dto.UpdatePartnerInput;
import br.com.event.management.system.core.events.application.service.PartnerService;
import br.com.event.management.system.core.events.domain.entities.Partner;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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

  @PutMapping("/{partnerId}")
  public ResponseEntity<Partner> update(@PathVariable final UUID partnerId, @RequestBody final UpdatePartnerInput input) {
    final var response = this.partnerService.update(partnerId, input);
    return ResponseEntity.ok(response);
  }

}

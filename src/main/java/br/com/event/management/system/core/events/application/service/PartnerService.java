package br.com.event.management.system.core.events.application.service;

import br.com.event.management.system.core.events.application.dto.RegisterPartnerInput;
import br.com.event.management.system.core.events.application.dto.UpdatePartnerInput;
import br.com.event.management.system.core.events.domain.entities.Partner;

import java.util.List;
import java.util.UUID;

public interface PartnerService {

  List<Partner> findAll();

  Partner register(RegisterPartnerInput input);

  Partner update(UUID partnerId, UpdatePartnerInput input);

}

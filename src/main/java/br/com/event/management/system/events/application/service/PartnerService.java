package br.com.event.management.system.events.application.service;

import br.com.event.management.system.common.domain.valueobjects.PartnerId;
import br.com.event.management.system.events.application.dto.RegisterPartnerInput;
import br.com.event.management.system.events.application.dto.UpdatePartnerInput;
import br.com.event.management.system.events.domain.entities.Partner;

import java.util.List;

public interface PartnerService {

  List<Partner> list();

  Partner register(RegisterPartnerInput input);

  Partner update(PartnerId id, UpdatePartnerInput input);

}

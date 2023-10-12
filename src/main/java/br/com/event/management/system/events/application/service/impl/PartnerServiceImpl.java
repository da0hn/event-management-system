package br.com.event.management.system.events.application.service.impl;

import br.com.event.management.system.common.application.UnitOfWork;
import br.com.event.management.system.common.domain.exception.DomainEntityNotFoundException;
import br.com.event.management.system.common.domain.valueobjects.PartnerId;
import br.com.event.management.system.events.application.dto.RegisterPartnerInput;
import br.com.event.management.system.events.application.dto.UpdatePartnerInput;
import br.com.event.management.system.events.application.service.PartnerService;
import br.com.event.management.system.events.domain.commands.CreatePartnerCommand;
import br.com.event.management.system.events.domain.entities.Partner;
import br.com.event.management.system.events.domain.repositories.PartnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PartnerServiceImpl implements PartnerService {

  private final PartnerRepository partnerRepository;

  private final UnitOfWork unitOfWork;

  @Override
  public List<Partner> findAll() {
    return this.partnerRepository.findAll();
  }

  @Override
  public Partner register(final RegisterPartnerInput input) {
    return this.unitOfWork.execute((transaction) -> {
      final var newPartner = Partner.create(new CreatePartnerCommand(input.name()));
      this.partnerRepository.add(newPartner);
      return newPartner;
    });
  }

  @Override
  public Partner update(final UUID partnerId, final UpdatePartnerInput input) {
    return this.unitOfWork.execute((transaction) -> {
      final var partner = this.findPartnerOrElseThrow(partnerId);

      partner.changeName(input.name());

      this.partnerRepository.add(partner);
      return partner;
    });
  }

  private Partner findPartnerOrElseThrow(final UUID id) {
    return this.partnerRepository.findById(PartnerId.of(id))
      .orElseThrow(() -> new DomainEntityNotFoundException("Partner not found"));
  }

}
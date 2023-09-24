package br.com.event.management.system.events.infrastructure.database;

import br.com.event.management.system.events.domain.CreatePartnerCommand;
import br.com.event.management.system.events.domain.Partner;
import br.com.event.management.system.events.infrastructure.database.model.PartnerModel;
import br.com.event.management.system.events.infrastructure.database.repositories.PartnerModelJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@ActiveProfiles("test-containers")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PartnerModelTest {

  @Autowired
  private PartnerModelJpaRepository partnerModelRepository;

  @BeforeEach
  void setUp() {
    this.partnerModelRepository.deleteAll();
  }

  @Test
  @DisplayName("Should save a partner")
  void test1() {

    final var partner = Partner.create(new CreatePartnerCommand("Partner 1"));

    final var partnerModel = new PartnerModel(partner.getId().value(), partner.getName());

    this.partnerModelRepository.save(partnerModel);

    final var maybePartnerModel = this.partnerModelRepository.findById(partner.getId().value());

    Assertions.assertThat(maybePartnerModel).isPresent();
    Assertions.assertThat(maybePartnerModel.get().getName()).isEqualTo(partner.getName());
    Assertions.assertThat(maybePartnerModel.get().getId()).isEqualTo(partner.getId().value());
  }

}

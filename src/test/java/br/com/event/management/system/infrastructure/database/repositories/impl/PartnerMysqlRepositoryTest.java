package br.com.event.management.system.infrastructure.database.repositories.impl;

import br.com.event.management.system.core.events.domain.commands.CreatePartnerCommand;
import br.com.event.management.system.core.events.domain.entities.Partner;
import br.com.event.management.system.infrastructure.events.database.repositories.impl.PartnerMysqlRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.TransactionTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ActiveProfiles("test-containers")
@DisplayName("Partner Repository Tests")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PartnerMysqlRepositoryTest {

  @Autowired
  private PartnerMysqlRepository repository;

  @Autowired
  private PlatformTransactionManager platformTransactionManager;

  @Test
  @DisplayName("Should execute all repository operations")
  void test1() {

    final var transactionTemplate = new TransactionTemplate(this.platformTransactionManager);
    transactionTemplate.setIsolationLevel(Isolation.SERIALIZABLE.value());
    transactionTemplate.setPropagationBehavior(Propagation.REQUIRES_NEW.value());

    final var partner = Partner.create(new CreatePartnerCommand("Partner 1"));

    transactionTemplate.executeWithoutResult(status -> {
      this.repository.add(partner);

      status.flush();

      final var maybePartnerModel = this.repository.findById(partner.getId());

      Assertions.assertThat(maybePartnerModel).isPresent();
      Assertions.assertThat(maybePartnerModel.get().getName()).isEqualTo(partner.getName());
      Assertions.assertThat(maybePartnerModel.get().getId()).isEqualTo(partner.getId());

      partner.changeName("Partner 2");
      this.repository.add(partner);
      status.flush();

      final var maybeUpdatedPartnerModel = this.repository.findById(partner.getId());

      Assertions.assertThat(maybeUpdatedPartnerModel).isPresent();
      Assertions.assertThat(maybeUpdatedPartnerModel.get().getName()).isEqualTo("Partner 2");
      Assertions.assertThat(maybeUpdatedPartnerModel.get().getId()).isEqualTo(partner.getId());

      final var allPartners = this.repository.findAll();
      Assertions.assertThat(allPartners).hasSize(1);

      this.repository.remove(partner);
      status.flush();

      Assertions.assertThat(this.repository.findAll()).isEmpty();
    });

  }

}

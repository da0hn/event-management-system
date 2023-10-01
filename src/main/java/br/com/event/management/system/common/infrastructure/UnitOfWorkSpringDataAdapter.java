package br.com.event.management.system.common.infrastructure;

import br.com.event.management.system.common.application.UnitOfWork;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
@AllArgsConstructor
public class UnitOfWorkSpringDataAdapter implements UnitOfWork {

  private final TransactionTemplate transactionTemplate;

  @Override
  public <T> T execute(final Function<? super TransactionActions, T> function) {
    return this.transactionTemplate.execute(
      status -> function.apply(new TransactionTemplateAdapter(status, this.transactionTemplate.getTransactionManager()))
    );
  }

  @Override
  public <T> T execute(
    final Function<? super TransactionActions, T> function,
    final BiConsumer<? super TransactionActions, ? super Throwable> errorHandler
  ) {
    return this.transactionTemplate.execute(
      status -> {
        final var tx = new TransactionTemplateAdapter(status, this.transactionTemplate.getTransactionManager());
        try {
          return function.apply(tx);
        }
        catch (final Exception exception) {
          errorHandler.accept(
            tx,
            exception
          );
          return null;
        }
      }
    );
  }

  @Override
  public void execute(final Consumer<? super TransactionActions> voidFunction) {
    this.transactionTemplate.executeWithoutResult(status -> {
      voidFunction.accept(new TransactionTemplateAdapter(status, this.transactionTemplate.getTransactionManager()));
    });
  }

  @Validated
  public record TransactionTemplateAdapter(
    @NotNull TransactionStatus status,
    @NotNull PlatformTransactionManager transactionManager
  ) implements TransactionActions {

    @Override
    public void commit() {
      this.status.flush();
      this.transactionManager.commit(this.status);
    }

    @Override
    public Object createSavepoint() {
      return this.status.createSavepoint();
    }


    @Override
    public void rollback() {
      this.status.setRollbackOnly();
    }

  }

}

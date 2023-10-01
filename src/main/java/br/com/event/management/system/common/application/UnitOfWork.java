package br.com.event.management.system.common.application;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public interface UnitOfWork {

  <T> T execute(Function<? super TransactionActions, T> function);

  <T> T execute(
    Function<? super TransactionActions, T> function,
    BiConsumer<? super TransactionActions, ? super Throwable> errorHandler
  );

  void execute(Consumer<? super TransactionActions> function);

  interface TransactionActions {

    void commit();

    Object createSavepoint();

    void rollback();

  }

}

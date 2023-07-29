package core.basesyntax.strategy;

import core.basesyntax.db.FruitStorage;
import core.basesyntax.model.FruitTransaction;

public class PurchaseOperationHandlerImpl implements OperationHandler {
    @Override
    public void handleOperation(FruitTransaction transaction) {
        String fruitName = transaction.getFruit();
        int quantity = transaction.getQuantity();
        if (FruitStorage.getFruit(fruitName).isPresent()) {
            FruitTransaction fruit = FruitStorage.getFruit(fruitName).get();
            int updatedQuantity = fruit.getQuantity() - quantity;
            if (updatedQuantity < 0) {
                throw new RuntimeException("Invalid fruit "
                        + "quantity after purchase: " + fruitName);
            }
            fruit.setQuantity(updatedQuantity);
        }
    }
}

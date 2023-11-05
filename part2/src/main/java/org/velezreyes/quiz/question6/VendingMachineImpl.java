package org.velezreyes.quiz.question6;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineImpl implements VendingMachine {

  private int credit;
  private final List<Drink> drinks;

  public VendingMachineImpl() {
    this.credit = 0;
    drinks = new ArrayList<>();
    drinks.add(new Drink() {
      @Override
      public String getName() {
        return "ScottCola";
      }

      @Override
      public boolean isFizzy() {
        return true;
      }
    });

    drinks.add(new Drink() {
      @Override
      public String getName() {
        return "KarenTea";
      }

      @Override
      public boolean isFizzy() {
        return false;
      }
    });
  }

  public static VendingMachine getInstance() {
    return new VendingMachineImpl();
  }

  private boolean listContainsDrink(String drinkName) {
    for (Drink drink : drinks) {
      if (drink.getName().equals(drinkName)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void insertQuarter() {
    this.credit += 25;
  }

  @Override
  public Drink pressButton(String name) throws NotEnoughMoneyException, UnknownDrinkException {
    if (credit == 0) {
      throw new NotEnoughMoneyException();
    }

    if (!listContainsDrink(name)) {
      throw new UnknownDrinkException();
    }

    if (name.equals("ScottCola")) {
      if (credit >= 75) {
        credit -= 75;
        return drinks.get(0);
      } else {
        throw new NotEnoughMoneyException();
      }
    }
    if (name.equals("KarenTea")) {
      if (credit >= 100) {
        credit -= 100;
        return drinks.get(1);
      } else {
        throw new NotEnoughMoneyException();
      }
    }
    return null;
  }
}

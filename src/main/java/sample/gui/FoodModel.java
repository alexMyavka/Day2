package sample.gui;

import java.util.ArrayList;

public class FoodModel {
    ArrayList<Food> foodList = new ArrayList<>();
    private int counter = 1;
    public void load() {
        foodList.clear();
        this.add(new Fruit(100, "Яблоко", true), false);
        this.add(new Chocolate(200, "шоколад Аленка", Chocolate.Type.milk), false);
        this.add(new Cookie(300, "сладкая булочка с Маком", true, true, false), false);
        this.emitDataChanged();
    }
    public DataChangedListener dataChangedListener;
    private ArrayList<DataChangedListener> dataChangedListeners = new ArrayList<>();
    public interface DataChangedListener {
        void dataChanged(ArrayList<Food> foodList);
    }
    public void addDataChangedListener(DataChangedListener listener) {
        this.dataChangedListeners.add(listener);
    }
    public void add(Food food) {
        add(food, true);
    }
    public void add(Food food, boolean emit) {
        food.id = counter;
        counter += 1;

        this.foodList.add(food);

        if (emit) {
            this.emitDataChanged();
        }
    }
    public void edit(Food food) {
        for (int i = 0; i< this.foodList.size(); ++i) {
            if (this.foodList.get(i).id == food.id) {
                this.foodList.set(i, food);
                break;
            }
        }

        this.emitDataChanged();
    }
    public void delete(int id)
    {
        for (int i = 0; i< this.foodList.size(); ++i) {
            if (this.foodList.get(i).id == id) {
                this.foodList.remove(i);
                break;
            }
        }
        this.emitDataChanged();
    }
    private void emitDataChanged() {
        for (DataChangedListener listener: dataChangedListeners) {
            listener.dataChanged(foodList);
        }
    }

}

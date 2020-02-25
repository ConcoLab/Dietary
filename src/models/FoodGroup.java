package models;

public class FoodGroup {

    // ID numbers for food and group
    private long foodId;
    private long groupId;

    // default constructor
    public FoodGroup() {
    }

    // sets foodId and groupId
    public FoodGroup(long foodId, long groupId){
        this.setFoodId(foodId);
        this.setGroupId(groupId);
    }


    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

}

/*
Made changes to FoodGroup.

The regular constructor is the same as before (just a default), and so none of the code is
affected unless the constructor with parameters is used in the code.

The parameterized constructor will accept some extra booleans for the actual food types to be
'extended' by whatever Food is being created. So, if you create a new Food called cheeseburger,
then you can have it as, say:

dairy = True
meat = True
carbohydrates = True
fruits = False
vegetables = False
sweets = False

Or, alternatively, this code could be changed to accept longs instead of booleans and instead represent
'fractions' of a daily serving. All of this can be input in the Food class, it just keeps it nicely separated.

Although this may not work for other reasons.

I will change this soon. NO NEED TO MERGE! It is just so I can keep track and experiment with Git.

 */

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

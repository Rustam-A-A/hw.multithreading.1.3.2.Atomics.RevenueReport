public class Revenue {
    public int checkAmount;
    public int maxCheckValue;

    public Revenue(int checkAmount, int maxCheckValue){
        this.checkAmount = checkAmount;
        this.maxCheckValue = maxCheckValue;
    }

    public int[] formShopReport(){
        int[] array = new int[checkAmount];
        for (int i = 0; i < array.length; i++){
            array[i] = (int) Math.round((Math.random() * maxCheckValue));
        }
        return array;
    }
}

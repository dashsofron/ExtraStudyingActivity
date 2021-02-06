public class FindNotSimple {

    public boolean findNotSimple(int[] arr){
        if(arr==null)return false;
        for (int value : arr) {
            for (int k = 2; k <= Math.sqrt(value); k++) {
                    if (value % k == 0) return  true;
            }
        }
        return false;
    }


}

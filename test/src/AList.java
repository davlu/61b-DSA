public class AList<item> implements List<item> {
    public void nigger(){
        System.out.println("A-OK!");
    }
    private static List<String> penis(AList<String> p){
        return p;
    }
    public static void main(String[] args){
        AList<String> a0 = new AList<>();
        AList<String> a1 = (AList) penis(a0);
        //uses dynamic type AList's print method
    }
}
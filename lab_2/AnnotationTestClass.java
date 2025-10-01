package lab2;

class AnnotationTestClass {
    @RepeatCall(0)
    public void publicMethod1(byte n){
        System.out.println("Public method 1 | n = " + n);
    }
    @RepeatCall(1)
    public void publicMethod2(int a, int b){
        System.out.println("Public method 2 | a = " + a + ", b = " + b);
    }

    @RepeatCall(3)
    protected void protectedMethod1(double n){
        System.out.println("Protected method 1 | n^2 = " + n*n);
    }
    @RepeatCall(2)
    protected void protectedMethod2(String text){
        System.out.println("Protected method 2 | text = " + text);
    }

    @RepeatCall(4)
    private  void privateMethod1(float a){
        System.out.println("Private method 1 | a = " + a);
    }

    private  void privateMethod2(short b){
        System.out.println("Private method 2 | b = " + b);
    }
}
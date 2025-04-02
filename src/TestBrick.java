public class TestBrick {
    public static void main(String[] args) {
        BrickLayout b = new BrickLayout("src/bricks", 39, false);
        b.printBrickLayout();
        b.doOneBrick();
        b.printBrickLayout();
    }
}
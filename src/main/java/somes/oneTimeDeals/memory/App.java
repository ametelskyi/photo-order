package somes.oneTimeDeals.memory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by anme on 28.03.17.
 */
public class App {
    public static void main(String[] args) {
        IntStream.range(0, 40).forEach(i -> {
            makeLabyrinth(11, 11, false );
            System.out.println();
            System.out.println("---------");
            System.out.println();
        });

    }

    private static void makeLabyrinth(int arrSize, int steps, boolean printArray) {
        int middle = arrSize/2;
        int[][] array = new int[arrSize][arrSize];
        init(array);

        for (int i = 0; i < steps; i++) {
            nextStep(array);
        }

        if(printArray) printArray(array);

        printPath(array, middle, middle, true);
        System.out.println();
        System.out.println("-");
        System.out.println();
        printPath(array, findMaxPlace(array)[0], findMaxPlace(array)[1], false);
    }

    private static boolean nextStep(int[][] array) {
        int[] now = findMaxPlace(array);
        int nowRow = now[0];
        int nowCol = now[1];

        int maxValue = getValue(array, now);

        boolean canGoUp = nowRow > 0 && array[nowRow-1][nowCol] == -1;
        boolean canGoDown = nowRow < array.length-1  && array[nowRow+1][nowCol] == -1;
        boolean canGoLeft = nowCol > 0 && array[nowRow][nowCol-1] == -1;
        boolean canGoRight = nowCol < array.length -1 && array[nowRow][nowCol+1] == -1;



        int[] ints = Arrays.asList(canGoUp, canGoDown, canGoLeft, canGoRight).stream().mapToInt(b -> b?1:0).toArray();
        int allVariants = Arrays.stream(ints).sum();
        if(allVariants == 0) return false;

        Map<Direction, Boolean> situation = new HashMap<>();
        situation.put(Direction.goUp, canGoUp);
        situation.put(Direction.goDown, canGoDown);
        situation.put(Direction.goLeft, canGoLeft);
        situation.put(Direction.goRight, canGoRight);

        Direction lastMove = getLastMove(array, maxValue, nowRow, nowCol);
        int lastLineLength = getLastLineLength(array, maxValue, now);

        double[] possibilities = new double[4];
        if(canGoUp) possibilities[0] = 1;
        if(canGoDown) possibilities[1] = 1;
        if(canGoLeft) possibilities[2] = 1;
        if(canGoRight) possibilities[3] = 1;

        if(lastLineLength > 0) {
            if(lastMove == Direction.goDown) possibilities[0] = possibilities[0] /(lastLineLength*3);
            if(lastMove == Direction.goUp) possibilities[1] = possibilities[1] /(lastLineLength*3);
            if(lastMove == Direction.goLeft) possibilities[2] = possibilities[2] /(lastLineLength*3);
            if(lastMove == Direction.goRight) possibilities[3] = possibilities[3] /(lastLineLength*3);
        }

        int step = getRandomStep(possibilities);

        if(step == 0) array[nowRow-1][nowCol] = maxValue+1; //UP
        if(step == 1) array[nowRow+1][nowCol] = maxValue+1; //DOWN
        if(step == 2) array[nowRow][nowCol-1] = maxValue+1; //LEFT
        if(step == 3) array[nowRow][nowCol+1] = maxValue+1; //RIGHT

        return true;
    }


    private static int getLastLineLength(int[][] array, int maxValue, int[] now) {
        Direction lastDirection = getLastMove(array, maxValue, now);
        if(lastDirection == null) return 0;
        int length = 1;
        int step = maxValue -1;

        while(getLastMove(array, step, findValuePlace(array, step)) == lastDirection){
            length++;
            step--;
        }
        return length;
    }

    private static Direction getLastMove(int[][] array, int maxValue, int[] now) {
        return getLastMove(array, maxValue, now[0], now[1]);
    }

    private static Direction getLastMove(int[][] array, int maxValue, int nowRow, int nowCol) {
        if(maxValue == 0) return null;
        if(getValue(array, new int[] {nowRow-1, nowCol}) == maxValue -1) return Direction.goUp;
        if(getValue(array, new int[] {nowRow+1, nowCol}) == maxValue -1) return Direction.goDown;
        if(getValue(array, new int[] {nowRow, nowCol-1}) == maxValue -1) return Direction.goRight;
        if(getValue(array, new int[] {nowRow, nowCol+1}) == maxValue -1) return Direction.goLeft;
        return null;
    }


    private static void printPath(int[][] array, int currentRow, int currentCol, boolean forward) {
        int now = array[currentRow][currentCol];
        int size = array.length;
        int nextVal = forward ? now +1 : now -1;
        if(nextVal == -1) {
            System.out.println();
            return;
        }

        if(currentRow >0 && array[currentRow-1][currentCol] == nextVal) {
            System.out.printf("%7s", "UP ");
            printPath(array, currentRow -1, currentCol, forward);
        } else if(currentRow <size-1 && array[currentRow+1][currentCol] == nextVal) {
            System.out.printf("%7s", "DOWN ");
            printPath(array, currentRow +1, currentCol, forward);
        } else if(currentCol > 0 && array[currentRow][currentCol-1] == nextVal) {
            System.out.printf("%7s", "LEFT ");
            printPath(array, currentRow, currentCol -1, forward);
        } else if(currentCol < size-1 && array[currentRow][currentCol +1] == nextVal) {
            System.out.printf("%7s", "RIGHT ");
            printPath(array, currentRow, currentCol +1, forward);
        }
    }

    private static int getValue(int[][] array, int[] coord) {
        if(coord[0]< 0 || coord[0] >= array.length) return -1;
        if(coord[1]< 0 || coord[1] >= array.length) return -1;
        return array[coord[0]][coord[1]];
    }

    private static int[] findMaxPlace(int[][] array) {
        int max = -1;
        int[] place = new int[]{0,0};
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(max < array[i][j]) {
                    max = array[i][j];
                    place[0] = i;
                    place[1] = j;
                }
            }
        }

        return place;
    }

    private static int[] findValuePlace(int[][] array, int value) {
        int[] place = new int[]{0,0};
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(value == array[i][j]) {
                    place[0] = i;
                    place[1] = j;
                }
            }
        }

        return place;
    }

    private static void init(int[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = -1;
            }
        }
        int middle = array.length/2;
        array[middle][middle] = 0;
    }

    private static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(array[i][j]==-1) System.out.print("   ");
                else System.out.printf("%2d ", array[i][j] );
            }
            System.out.println();
        }
    }

    enum Direction {
        goUp,
        goDown,
        goLeft,
        goRight
    }


    private static int getRandomStep(double[] possibilities) {
        int length =0;
        double sum = 0;
        for (double v : possibilities) {
            if(v > 0) {
                length++;
                sum += v;
            }
        }

        double[] significant = new double[length];
        int[] mapping = new int[possibilities.length];

        int num = 0;
        for (int i = 0; i < possibilities.length; i++) {
            if(possibilities[i] >0) {
                significant[num] = possibilities[i];
                mapping[i] = num;
                num++;
            } else {
                mapping[i] = -1;
            }
        }

        double rnd =Math.random()*sum;


        int maxIndex = 0;
        double currentSum = 0;
        for (int i = 0; i < significant.length; i++) {
            if(currentSum < rnd) maxIndex = i;
            currentSum += significant[i];
        }

        int resultIndex = -1;
        for (int i = 0; i < mapping.length; i++) {
            if(mapping[i] == maxIndex) resultIndex = i;
        }

        return resultIndex;
    }

}

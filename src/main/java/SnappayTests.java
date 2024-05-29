import java.util.ArrayList;
import java.util.List;

public class SnappayTests {

    static char[][] keyboard = {
            {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'},
            {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'},
            {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';'},
            {'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/'}
    };

    public static void main(String[] args) {
        String input = "-1234";
        String text = "A Bullock run, which he is expected to announce sometime before Monday's filing deadline, will have reverberations well beyond the Last Best Place.\n" +
                "A reminder of the majority math for Democrats makes clear why. To retake the majority, Democrats need to net three seats if their side wins the White House and four if they don't.\n" +
                "Before Bullock's decision, non-partisan handicappers -- like the Cook Political Report -- rated three GOP seats as deeply imperiled: Arizona Sen. Martha McSally, Colorado Sen. Cory Gardner and Maine Sen. Susan Collins. But Democrats also have to defend Sen. Doug Jones (D) in GOP-friendly Alabama, a prospect that got much harder when uber-controversial Roy Moore didn't make the GOP runoff earlier this week.\n" +
                "While Bullock is no sure thing -- Daines had $5 million in the bank at the end of 2019 -- his candidacy absolutely makes the race competitive. Put another way, it gives Democrats a bit more margin for error in their majority push. In addition to the trio of endangered GOP incumbents, Montana now joins a second group of GOP-held seats where Democrats expect to have a real chance, which includes: Georgia Sen. Kelly Loeffler, North Carolina Sen. Thom Tillis and the open Kansas seat.\n" +
                "For Democrats to ensure they take back the majority then, the two obvious scenarios are:\n" +
                "1) Get Jones reelected, sweep the three most endangered GOP seats and win one of Montana, Georgia, North Carolina or Kansas\n" +
                "2) Jones loses, meaning Democrats need to win five Republican seats from Arizona, Colorado, Maine, Montana, Georgia, North Carolina or Kansas.\n" +
                "Adding another seat to that mix -- and one that was simply off the board without Bullock -- allows Democrats some leeway to lose, say, in Georgia, and not have their chances at the majority disappear.\n" +
                "To be clear: Bullock running boosts Democratic chances. But it does not make the majority a sure thing. Not even close. Given the landscape of states where Democrats need to win in order to retake the majority, it's still significantly less than a 50-50 proposition.";

        System.out.println("start tests with input: "+input);

        String result;
        switch (input) {
            case "H":
                result = flipHorizontal(text);
                break;
            case "V":
                result = flipVertical(text);
                break;

            default: result = shift(input, text);
        }

        System.out.println(result);
    }

    private static List<Position> generateOriginPos(String text) {
        text = text.toLowerCase();

        Integer l = text.length();
        List<Position> positions = new ArrayList<>();
        for (int i=0;i<l;i++) {
            char c = text.charAt(i);
            boolean found = false;
            for (int j=0;j<keyboard.length;j++) {
                for (int k=0;k<keyboard[0].length;k++) {
                    if (c == keyboard[j][k]) {
                        positions.add(new Position(j,k));
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }

            if (!found) positions.add(new Position(-1,-1));
        }

        return positions;
    }

    private static String flipHorizontal(String text) {
        List<Position> originPos = generateOriginPos(text);

        for (int i=0;i<keyboard.length;i++) {
            for (int j=0;j<5;j++) {
                char tmp = keyboard[i][j];
                keyboard[i][j] = keyboard[i][9-j];
                keyboard[i][9-j] = tmp;
            }
        }

        return convertByKB(originPos, keyboard);
    }

    private static String flipVertical(String text) {
        List<Position> originPos = generateOriginPos(text);

        for (int i=0;i<2;i++) {
            for (int j=0;j<keyboard[0].length;j++) {
                char tmp = keyboard[i][j];
                keyboard[i][j] = keyboard[3-i][j];
                keyboard[3-i][j] = tmp;
            }
        }

        return convertByKB(originPos, keyboard);
    }

    private static String shift(String _offset, String text) {
        List<Position> originPos = generateOriginPos(text);
        int offset = Integer.parseInt(_offset);
        int rl = keyboard.length, cl = keyboard[0].length;

        char[][] shiftKeyboard = new char[rl][cl];

        for (int i=0;i<rl;i++) {
            for (int j=0;j<cl;j++) {
                if (j+offset<0) offset = rl * cl + (- offset % (rl * cl));
                int newR = (i + (j+offset)/cl ) % 4;
                int newC = (j+offset) % cl;
                shiftKeyboard[newR][newC] = keyboard[i][j];

            }
        }

        printArray(shiftKeyboard);

        return convertByKB(originPos, shiftKeyboard);
    }

    private static String convertByKB(List<Position> originPos, char[][] keyboard) {
        StringBuilder sb = new StringBuilder();
        for (Position pos : originPos) {
            if (pos.row == -1 || pos.column == -1) sb.append(' ');
            else sb.append(keyboard[pos.row][pos.column]);
        }

        return sb.toString();
    }

    private static class Position {
        Integer row;
        Integer column;

        Position(int r, int c) {
            this.row = r;
            this.column = c;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", row, column);
        }

    }

    private static void printList(List list) {
        System.out.print("[");
        for(Object obj : list) {
            System.out.print(obj+" ");
        }
        System.out.println("]");
    }

    private static void printArray(char[][] arr) {
        System.out.print("[");
        for (int i=0;i<arr.length;i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(String.format("%s ", arr[i][j]));
            }
        }
        System.out.println("]");
    }

}

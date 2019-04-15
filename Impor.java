package eg.edu.alexu.cad.datastructure.iceHockey;

public class Impor {
    public static  void main(String[] args) {
        String[] arr ={
                "8D88888J8L8E888", "88NKMG8N8E8JI88", "888NS8EU88HN8EO", "LUQ888A8TH8OIH8", "888QJ88R8SG88TY", "88ZQV88B88OUZ8O", "FQ88WF8Q8GG88B8", "8S888HGSB8FT8S8", "8MX88D88888T8K8", "8S8A88MGVDG8XK8", "M88S8B8I8M88J8N", "8W88X88ZT8KA8I8", "88SQGB8I8J88W88", "U88H8NI8CZB88B8", "8PK8H8T8888TQR8" };
        java.awt.Point[] result = findPlayers(arr, 8, 9);
        for (int i = 0; i < result.length; i++)
            System.out.println("(" + result[i].x + "," + result[i].y + ")");
    }

    public static java.awt.Point[] findPlayers(String[] photo, int team, int threshold) {
        int n = photo.length;
        int m = photo[0].length();
        char essam = (char) (team + 48);
        int counterForPlayers = 0;
        java.awt.Point[] players = new java.awt.Point[n * m];
        char[][] arr = new char[n][m];
        char[][] temp = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = ' ';
                temp[i][j] = ' ';
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (photo[i].charAt(j) == essam) {
                    arr[i][j] = photo[i].charAt(j);
                }
            }
        }
        boolean isCorrupt = true ;
        for(int y= 0 ; y<n;y++){
            for(int t = 0 ; t<m;t++){
                if(arr[y][t]==essam)
                    isCorrupt=false;
            }
        }
        if(isCorrupt){
            java.awt.Point[] ahmed = new java.awt.Point[1] ;
            return ahmed ;
        }
        int i = 0, j = 0;
        while (true && i < n && j < m) {
            boolean isFound = false;
            for (i = i; i < n; i++) {
                for (j = j; j < m; j++) {
                    if (arr[i][j] == essam) {
                        isFound = true;
                        temp[i][j] = essam;
                        if (i != n - 1) {
                            if (arr[i + 1][j] == essam)
                                temp[i + 1][j] = essam;
                        }
                        if (j != m - 1) {
                            if (arr[i][j + 1] == essam)
                                temp[i][j + 1] = essam;
                        }
                        if (i != 0) {
                            if (arr[i - 1][j] == essam)
                                temp[i - 1][j] = essam;
                        }
                        if (j != 0) {
                            if (arr[i][j - 1] == essam)
                                temp[i][j - 1] = essam;
                        }
                        arr[i][j] = ' ';
                    }
                    if (isFound)
                        break;
                }
                if (isFound)
                    break;
                if (j == m)
                    j = 0;
            }
            boolean isHere = false;
            for (int i1 = 0; i1 < n; i1++) {
                for (int j1 = 0; j1 < m; j1++) {
                    if (temp[i1][j1] == essam && arr[i1][j1] == essam) {
                        isHere = true;
                        i = i1;
                        j = j1;
                        break;
                    }
                    if (isHere)
                        break;
                }
                if (isHere)
                    break;
            }
            if (isHere == false) {
                int counter = 0;
                for (int i1 = 0; i1 < n; i1++) {
                    for (int j1 = 0; j1 < m; j1++) {
                        if (temp[i1][j1] == essam)
                            counter++;
                    }
                }
                if (counter * 4 >= threshold) {
                    int[] saveI = new int[2];
                    int[] saveJ = new int[2];
                    int counter2 = 0;
                    for (int i1 = 0; i1 < n; i1++) {
                        for (int j1 = 0; j1 < m; j1++) {
                            if (temp[i1][j1] == essam && counter2 == 0) {
                                saveI[0] = i1;
                                counter2++;
                            }
                            if (temp[i1][j1] == essam)
                                saveI[1] = i1;
                        }
                    }
                    counter2 = 0;
                    for (int j1 = 0; j1 < m; j1++) {
                        for (int i1 = 0; i1 < n; i1++) {
                            if (temp[i1][j1] == essam && counter2 == 0) {
                                saveJ[0] = j1;
                                counter2++;
                            }
                            if (temp[i1][j1] == essam)
                                saveJ[1] = j1;
                        }
                    }
                    int[] borders = new int[4];
                    borders[0] = 2 * saveI[0];
                    borders[1] = 2 * saveJ[0];
                    borders[2] = 2 * saveI[1] + 2;
                    borders[3] = 2 * saveJ[1] + 2;
                    int w = borders[1] + (borders[3] - borders[1]) / 2;
                    int z = borders[0] + (borders[2] - borders[0]) / 2;
                    java.awt.Point ahmed = new java.awt.Point(w, z);
                    players[counterForPlayers] = ahmed;
                    for (int i1 = 0; i1 < n; i1++) {
                        for (int j1 = 0; j1 < m; j1++) {
                            temp[i1][j1] = ' ';
                        }
                    }
                    i = 0;
                    j = 0;
                    counterForPlayers++;
                }
                if (counter * 4 < threshold) {
                    for (int i1 = 0; i1 < n; i1++) {
                        for (int j1 = 0; j1 < m; j1++) {
                            temp[i1][j1] = ' ';
                        }
                    }
                    i = 0;
                    j = 0;
                }
            }
            boolean isEmpty = true;
            for (int i1 = 0; i1 < n; i1++) {
                for (int j1 = 0; j1 < m; j1++) {
                    if (arr[i1][j1] == essam) {
                        isEmpty = false;
                    }
                }
            }
            if (isEmpty)
                break;
        }
        java.awt.Point[] final1 = new java.awt.Point[counterForPlayers];
        for (int i1 = 0; i1 < counterForPlayers; i1++) {
            final1[i1] = players[i1];
        }
        for(int i2 = 0 ; i2<counterForPlayers;i2++){
            for(int j2 = 0 ; j2 <counterForPlayers-1 ; j2++){
                if(final1[j2].x>final1[j2+1].x){
                    int v , d ;
                    v = final1[j2].x;
                    d=final1[j2].y;
                    final1[j2].x=final1[j2+1].x;
                    final1[j2].y=final1[j2+1].y;
                    final1[j2+1].x=v;
                    final1[j2+1].y = d ;
                }
            }
        }
        return final1;
    }
} 
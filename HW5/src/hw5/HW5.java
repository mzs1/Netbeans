
// The actual code

import java.util.*;

public class HW5 {
    int N; // number of vertices in the graph
    boolean[][] G; // the graph as an adjacency matrix
    // G[i][j] is true if there is an edge from i to j
    int currPos;

    HW5(int size, int loc) {
        N = size;
        currPos = loc;
        setupGraph();
        System.out.println("----------Traverse-------------");
        System.out.println();
    }

    void setupGraph() {
        // set up a graph with 8 vertices that looks like:
        G = new boolean[N][N];
        G[0][1] = G[1][0] = true; // notice that for each edge G[i][j] == G[j][i]
        G[0][8] = G[8][0] = true; // means the graph is undirected
        G[0][4] = G[4][0] = true;
        G[1][2] = G[2][1] = true;
        G[1][3] = G[3][1] = true;
        G[2][6] = G[6][2] = true;
        G[3][4] = G[4][3] = true;
        G[3][5] = G[5][3] = true;
        G[6][7] = G[7][6] = true;
        G[8][9] = G[9][8] = true;
        G[9][10] = G[10][9] = true;
        G[10][11] = G[11][10] = true;
    }

    void HW5() {
        System.out.println("\n*** The current location is: " + retCity(currPos) + " ***\n");
        System.out.println(
                "Moving to the next destination: \n..[0] manually\n..[1] First In First Out (FIFO)\n..[2] Last In First Out (LIFO)]\n..[3] more connection\n..[4] DFS\n..[5] Breaadth First Search\n..[6] DFS with limit\n..[7] DFS Iterative\n..[-1] Stop");
        Scanner inp = new Scanner(System.in);
        int ch = inp.nextInt();
        switch (ch) // The user chooses the way to traverse the graph
        {
        case -1: // Stop
            System.out.println("The program's been stopped");
            break;
        case 0:
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < N; i++)
                if (G[currPos][i] == true)
                    list.add(i);
            System.out.println("\nChoose one of the following connected cities: ");
            for (int i = 0; i < list.size(); i++)
                System.out.println("[" + i + "]" + retCity(list.get(i)) + "\t");
            System.out.println("\nInput: ");
            ch = inp.nextInt();
            if (ch < 0 || ch >= list.size()) {
                System.out.println("The choice is not correct,try again");
                HW5();
            }
            currPos = list.get(ch);
            HW5();
            break;
        case 1: // FIFO
            String addToQ = "[(Front) ";
            Queue<Integer> Q = new LinkedList<Integer>(); // A queue to process nodes
            for (int i = 0; i < N; i++)
                if (G[currPos][i] == true) {
                    Q.offer(i);// The connected cities are added to the queue
                    addToQ += retCity(i) + "\t";
                }
            addToQ += "]\n";
            System.out.println(addToQ);
            System.out.println(retCity(Q.peek()) + " is the destination\n");
            currPos = Q.peek();
            HW5();
            break;
        case 2: // LIFO
            String addToSta = "";
            Stack<Integer> sta = new Stack<Integer>();
            addToSta += "[";
            for (int i = 0; i < N; i++)
                if (G[currPos][i] == true) {
                    sta.push(i);// The connected cities are added to the statck
                    System.out.println(retCity(i));
                    addToSta += retCity(i) + "\t";
                }
            addToSta += "top=>]";
            System.out.println(addToSta);
            currPos = sta.peek();
            HW5();
            break;
        case 3:
            List<Integer> listC = new ArrayList<Integer>();
            for (int i = 0; i < N; i++)
                if (G[currPos][i] == true)
                    listC.add(i);
            int citymax = 0;
            int citycount = 0;
            for (int i = 0; i < listC.size(); i++) {
                int c = 0;
                for (int j = 0; j < N; j++) {
                    if (G[listC.get(i)][j] == true) {
                        // System.out.println(i+"||"+j);
                        c++;
                    }
                }
                if (c > citycount) {
                    citycount = c;
                    citymax = i;
                }
                System.out.println(retCity(listC.get(i)) + " = " + c);
            }
            System.out.println("max conntion is = " + retCity(listC.get(citymax)) + " with " + citycount
                    + " conntion\nmoving to " + retCity(listC.get(citymax)));
            currPos = listC.get(citymax);
            HW5();
            break;
        case 4: // DFS
            List<Integer> visitedList = new ArrayList<Integer>();
            int goalCity;
            System.out.println("-------------------");
            for (int i = 0; i < 12; i++)// list of cities
                System.out.println(retCity(i) + " city[" + i + "]");
            System.out.println("-------------------");
            System.out.println("choose your goal:");
            goalCity = inp.nextInt();
            Stack<Integer> stack = new Stack<Integer>();
            visitedList.add(currPos);
            while (goalCity != currPos) {
                for (int i = 0; i < N; i++) {
                    if (G[currPos][i] == true && !visitedList.contains(i)) {
                        stack.push(i);
                    }
                }
                currPos = stack.pop();
                System.out.println(retCity(currPos));
                visitedList.add(currPos);
            }
            System.out.println("-------------------");
            System.out.println("you've reached your goal !!");
            HW5();
            break;
        case 5: // BFS ==============================================================
            List<Integer> visitedListQ = new ArrayList<Integer>();
            System.out.println("-------------------");
            for (int i = 0; i < 12; i++)
                System.out.println(retCity(i) + " city[" + i + "]");
            System.out.println("-------------------");
            System.out.println("choose your goal:");
            goalCity = inp.nextInt();
            Queue<Integer> queue = new LinkedList<Integer>();
            visitedListQ.add(currPos);
            while (goalCity != currPos) {
                for (int i = 0; i < N; i++) {
                    if (G[currPos][i] == true && !visitedListQ.contains(i)) {
                        queue.add(i);
                    }
                }
                currPos = queue.peek();
                if (!visitedListQ.contains(currPos)) {
                    System.out.println(retCity(currPos));
                    visitedListQ.add(currPos);
                }
                queue.remove();
            }
            System.out.println("-------------------");
            System.out.println("you've reached your goal !!");
            HW5();
            break;
        case 6: // DFS with limits LDFS =============================================
            List<Integer> visitedListL = new ArrayList<Integer>();
            int limit = 0;
            System.out.println("Enter Limit: ");
            limit = inp.nextInt();
            int L[] = new int[12];
            for (int i = 0; i < N; i++) {
                L[i] = N;
            }
            System.out.println("-------------------");
            for (int i = 0; i < 12; i++)// list of cities
                System.out.println(retCity(i) + " city[" + i + "]");
            System.out.println("-------------------");
            System.out.println("choose your goal:");
            goalCity = inp.nextInt();
            Stack<Integer> stackL = new Stack<Integer>();
            visitedListL.add(currPos);
            L[currPos] = 0;
            stackL.push(currPos);
            while (!stackL.empty()) {
                if (goalCity == currPos) {
                    System.out.println("-------------------");
                    System.out.println("you've reached your goal !!");
                    break;
                }
                currPos = stackL.pop();
                System.out.println(retCity(currPos) + " lvl = " + L[currPos]);
                visitedListL.add(currPos);
                for (int i = 0; i < N; i++) {
                    if (G[currPos][i] == true && !visitedListL.contains(i) && L[currPos] + 1 <= limit) {
                        stackL.push(i);
                        L[i] = L[i] > L[currPos] + 1 ? L[currPos] + 1 : L[i];
                    }
                }
            }
            HW5();
            break;
        case 7: // DFS with limits LDFS ==============================================
            int initalimit = 1;
            int LI[] = new int[N];
            for (int i = 0; i < N; i++) {
                LI[i] = 100;
            }
            System.out.println("-------------------");
            for (int i = 0; i < 12; i++)// list of cities
                System.out.println(retCity(i) + " city[" + i + "]");
            System.out.println();
            System.out.println("Enter Goal city: ");
            goalCity = inp.nextInt();
            int local = 100;
            List<Integer> visitedListI = new ArrayList<Integer>();
            LI[currPos] = 0;
            visitedListI.add(currPos);
            Stack<Integer> stackLI = new Stack<Integer>();
            while (true) {
                {
                    for (int j = 0; j < N; j++) {
                        if (G[currPos][j] == true && !visitedListI.contains(j)) {
                            stackLI.push(j);
                            LI[j] = LI[j] > LI[currPos] + 1 ? LI[currPos] + 1 : LI[j];
                            initalimit = LI[j];
                        }
                    }
                    if (stackLI.empty()) {
                        System.out.println("Stack is empty");
                        break;
                    } else {
                        local = stackLI.pop();
                        if (local == goalCity) {
                            System.out.println("-------------------");
                            System.out.println("you've reached your goal !!");
                            currPos = local;
                            break;
                        } else {
                            visitedListI.add(local);
                            currPos = local;
                        }
                    }
                }
            }
            limit = 0;
            while (limit != initalimit + 1) {
                System.out.println("-------------------");
                System.out.println("at lvl = " + limit);
                for (int i = 0; i < N; i++) {
                    if (LI[i] == limit) {
                        System.out.println(retCity(i));
                    }
                }
                limit++;
            }
            HW5();
            break;
        } // closing switch block
    } // closing TRA function

    public static void main(String[] args) {
        int cityChoice;
        Scanner inp = new Scanner(System.in);
        System.out.println("\n\nChoose a city number to start with: \n");
        for (int i = 0; i < 12; i++)// shows the list of cities
            System.out.println(retCity(i) + " city[" + i + "]");
        System.out.print("\nInput: ");
        cityChoice = inp.nextInt();// User's choice of the list
        if (cityChoice < 0 || cityChoice >= 12)// the user choice is not included in the list
        {
            System.out.println("Mistake,run the program again");
            System.exit(0);
        }
        HW5 traGraph = new HW5(12, cityChoice);
        traGraph.HW5();
    }

    public static String retCity(int i) // the function returns city name, according to its index in V array
    {
        if (i == 0)
            return "Buraydah";
        else if (i == 1)
            return "Unayzah";
        else if (i == 2)
            return "AlZulfi";
        else if (i == 3)
            return "Al-Badai";
        else if (i == 4)
            return "Riyadh-Alkhabra";
        else if (i == 5)
            return "AlRass";
        else if (i == 6)
            return "UmSedrah";
        else if (i == 7)
            return "Shakra";
        else if (i == 8)
            return "Al-Bukayriyah";
        else if (i == 9)
            return "Sheehyah";
        else if (i == 10)
            return "Dhalfa";
        else if (i == 11)
            return "Mulida";
        else
            return "error";
    }
}
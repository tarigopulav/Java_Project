import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Scanner;
import java.util.Map.Entry;
import java.io.PrintWriter;
@SuppressWarnings("unchecked")


public class GraphColoringSudoku {

    private static int total_nodes;           // no: of nodes (Total)
    private static int total_edges;           // no of edges: total
    private static List<Integer> colorList;        //color of each node
    private static int additionalColors = 0;   // additional colors
    private static List<Integer> colors = new ArrayList<>();      // unique available colors
    private static Graph graph;
    private static String outfilename;

    public static class Graph {
        // Using HashMap to generate key value pairs in the Implementation part
        public final Map<Integer, ArrayList<Integer>> adjtMAP;
        public Graph(int vertices, int nodes) {
            adjtMAP = new HashMap();
        }
        //Function to add new verex to the Graph...
        public void addNewVertex(int v){
            if (!adjtMAP.containsKey(v)) {
                ArrayList<Integer> neighbours = new ArrayList<>();
                adjtMAP.put(v, neighbours);
            }
        }
        //Function to add new edge to the Graph...
        public void addNewEdge(int v, int w) {
            adjtMAP.get(v).add(w);
        }
    }
    public static void main(String [ ] args) throws FileNotFoundException,IOException {
        System.out.println("Enter input filename(input file must be in the same directory as this file):" );
        Scanner sc=new Scanner(System.in);
        String lastp=sc.next();
        System.out.println("Enter output filename: ");
        outfilename=sc.next();
        File dir = new File(".");
        String firstp=dir.getCanonicalPath();
        File f = new File(firstp + File.separator +lastp);
        read(f);           //reading the file

        //Actual implementation of greedy Coloring problem starts here.....
        int KeyNode;
        ArrayList<Integer> listofalledges;
        int ncolor;
        for (Entry<Integer, ArrayList<Integer>> e : graph.adjtMAP.entrySet())
        {
            KeyNode = e.getKey()-1;
            listofalledges = e.getValue();
            ncolor = colorList.get(KeyNode);
            if (ncolor == 0) {           // if node has no color
                List<Integer> templist = new ArrayList<>();
                for(int i=0;i<listofalledges.size();i++){
                    int col=listofalledges.get(i);
                    --col;
                    int elincolorlist=colorList.get(col);
                    if(!templist.contains(elincolorlist))
                        templist.add(elincolorlist);
                }
                List<Integer> list=new ArrayList<>();
                for(int el : colorList)
                {
                    if(!templist.contains(el))
                        list.add(el);
                }
                int selectedc;
                if (list.isEmpty())
                {
                    selectedc = colors.get(colors.size()-1)+1;
                    colors.add(selectedc);
                    additionalColors++;
                }
                else
                {
                    int i=1;
                    selectedc=list.get(list.size()-i);
                    while(!(selectedc>0))
                    {
                        i++;
                        selectedc=list.get(list.size()-i);
                    }
                }
                colorList.set(KeyNode, selectedc);
            }}
        displayOutput();}
    // Function to read input file....
    private static void read(File filein) throws FileNotFoundException,IOException {
        Scanner buffer=new Scanner(filein);
        String line;
        if(buffer.hasNextLine()){
            line = buffer.nextLine();
            String[] arr=line.split(" ");
            total_nodes = Integer.parseInt(arr[0]);
            total_edges = Integer.parseInt(arr[1]);
            graph = new Graph(total_nodes, total_edges);
        }
        else{
            System.out.println("File is Empty");
            return;}
        for(int i=0;i<total_edges;i++){
            line = buffer.nextLine();
            String[] arr=line.split(" ");
            graph.addNewVertex(Integer.parseInt(arr[0]));      //adding vertex
            graph.addNewEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));     //adding new edge
            graph.addNewVertex(Integer.parseInt(arr[1]));           //adding vertex
            graph.addNewEdge(Integer.parseInt(arr[1]), Integer.parseInt(arr[0]));    //adding new edge
        }
        line = buffer.nextLine();
        String arr[]=line.split(" ");
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<arr.length;i++)
        {
            int w=Integer.parseInt(arr[i]);
            list.add(w);
            if (!colors.contains(w)) {               //adding a new colo
                colors.add(w);
            }
        }
        colorList=list;
        Collections.sort(colors);
    }
    // Function to display output and generate to the output file...
    private static void displayOutput() throws IOException
    {
        String colorl= colorList.toString();
        colorl = colorl.replace("[", "");
        colorl = colorl.replace("]", "");
        System.out.println(additionalColors);
        System.out.println(colorl);
        File dir = new File(".");
        String dirp=dir.getCanonicalPath();
        File f = new File(dirp + File.separator +outfilename);
        if(!f.exists()) f.createNewFile();
        PrintWriter pw=new PrintWriter(f);
        pw.println(additionalColors);
        pw.println(colorl);
        pw.close();
    }


}


import java.util.*;

public class RandomGraphColoring {

    // Contains the corresponding color to each node
    private static List<Integer> colorList;

    // New colors needed
    private static int additionalColors = 0;

    // Contains unique available colors.
    private static List<Integer> colors = new ArrayList<>();

    // Function to generate random graphs...
    public static GraphColoringSudoku.Graph getRandomGraph(int NumberOfVertices, int NumberOfEdges){

        Graph graph = GraphGenerator.simple(NumberOfVertices, NumberOfEdges);

        GraphColoringSudoku.Graph retVal = new GraphColoringSudoku.Graph(NumberOfVertices, NumberOfEdges);

        colorList = new ArrayList<>();

        for (int i = 0; i < NumberOfVertices; i++) {
            Iterable<Integer> neighbours = graph.adj(i);
            retVal.addNewVertex(i+1);

            for (Integer neighbour : neighbours){
                retVal.addNewEdge(i+1, neighbour+1);
            }

            colorList.add(0);
        }
        return retVal;
    }

    public static void main(String [ ] args) throws Exception {
        GraphColoringSudoku.Graph graph = getRandomGraph(50, 100);
        //GraphColoringSudoku.Graph graph = getRandomGraph(100, 1000);
        //GraphColoringSudoku.Graph graph = getRandomGraph(150, 2000);
        //GraphColoringSudoku.Graph graph = getRandomGraph(300, 4000);
        //GraphColoringSudoku.Graph graph = getRandomGraph(400, 7000);
        //GraphColoringSudoku.Graph graph = getRandomGraph(500, 10000);
        //GraphColoringSudoku.Graph graph = getRandomGraph(1000, 100000);

        
        colors.add(0);

        // Greedy implementation
        int KeyNode;                    //implementation of greedy
        ArrayList<Integer> listofalledges;
        int ncolor;
        for (Map.Entry<Integer, ArrayList<Integer>> e : graph.adjtMAP.entrySet())
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
        String strNewColors = Integer.toString(additionalColors);
        String strFinalColors = colorList.toString();
        strFinalColors = strFinalColors.substring(1, strFinalColors.length()-1);

        String strFinal = strNewColors + "\\n" + strFinalColors;

        System.out.println(strNewColors);
        System.out.println(strFinalColors);
    }

    private static int getNewColor() {
        int lastColor = colors.get(colors.size() - 1);
        lastColor++;
        colors.add(lastColor);

        additionalColors++;
        return lastColor;
    }

}

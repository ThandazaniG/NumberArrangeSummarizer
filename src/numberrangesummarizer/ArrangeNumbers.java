package numberrangesummarizer;
import com.sun.xml.internal.fastinfoset.stax.events.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.StringTokenizer;

public class ArrangeNumbers implements NumberRangeSummarizer {
    /**
     * Collection of integers from input string
     * @param input string of numbers separated by a comma
     * @return collection of integers
     */
    @Override
    public Collection<Integer> collect(String input) {
      if(input == null || Util.isEmptyString(input.trim())) throw new RuntimeException("We can not collect and summarize null or empty input") ;
      if(!input.replace(",","").trim().matches("[0-9]+")) throw new RuntimeException("We can not collect and summarize non-integer input") ;

    Collection<Integer> inputNumbers = new ArrayList<>();
    StringTokenizer str = new StringTokenizer(input, ",");
    while (str.hasMoreTokens()) {

        inputNumbers.add(Integer.parseInt(str.nextToken()));
    }
    return removeDuplicatesAndSort(inputNumbers);

    }

    /**
     * Removes duplicates and sorts collection
     * @param collection collection of numbers
     * @return collection of distinct sorted integers
     */
    private Collection<Integer> removeDuplicatesAndSort(Collection<Integer> collection){
             return collection.stream().distinct().sorted().collect(Collectors.toList());
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if(input == null || input.size()==0) throw new RuntimeException("We can not summarize null or empty collection") ;

        if (input.size()==1){
            return Integer.toString((Integer)input.toArray()[0]);
        }
        ArrayList<Integer> out = (ArrayList<Integer>)input;
        StringBuilder answer = new StringBuilder();
        int position, next, start=0, end = 0;
        boolean test = false;
        int secondLast = out.get(out.size()-2);

        for(int i =0; i<out.size()-1; i++){
            position = out.get(i);
            next = out.get(i+1);
            if(next==position+1){
                if(!test){
                    start = position;
                }
                end = next;
                test = true;
            }
            else{
                if(test){
                    answer.append(Integer.toString(start)).append("-").append(Integer.toString(end)).append(",");
                    test = false;
                }
                else{
                    answer.append(Integer.toString(position)).append(",");
                }
            }
        }

        if(secondLast+1==out.get(out.size()-1)){
            end = out.get(out.size()-1);
            answer.append(Integer.toString(start)).append("-").append(Integer.toString(end));
        }
        else{
            answer.append(Integer.toString(out.get(out.size()-1)));
        }
        return answer.toString();
    }

}
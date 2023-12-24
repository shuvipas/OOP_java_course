import java.util.Scanner;

class Chat {
    public static void main(String[]  args){
        //System.out.println("Hello Java!");
// i need to call ChatterBot.REQUESTED_PHRASE_PLACEHOLDER 

        final int NUM_OF_BOTS = 2;
        String statement = "say hi unit 8200 + 8";

        ChatterBot[] bots = new ChatterBot[NUM_OF_BOTS];
        String[] illegalRequest1 = new String[]{"what" +ChatterBot.ILLEGAL_REQUEST_PLACEHOLDER+"?", "say"+ChatterBot.ILLEGAL_REQUEST_PLACEHOLDER+" i should say"};
        String[] illegalRequest2 = new String[]{"whaaat" + ChatterBot.ILLEGAL_REQUEST_PLACEHOLDER, "say"+ChatterBot.ILLEGAL_REQUEST_PLACEHOLDER+" say"};
        
        String[] LegalRequest = new String[]{"You want me to say <phrase>, do you? alright <phrase>",
         "say" + ChatterBot.REQUESTED_PHRASE_PLACEHOLDER + "? okey:"+ ChatterBot.REQUESTED_PHRASE_PLACEHOLDER};
        String[] names = new String[NUM_OF_BOTS];// {"ofek, ovadia"};
        for (int i = 0; i < NUM_OF_BOTS; i++) {
            names[i] = 'b'+Integer.toString(i);
        } 
        bots[0] = new ChatterBot(names[0],LegalRequest ,illegalRequest1);  
        bots[1] = new ChatterBot(names[1], LegalRequest ,illegalRequest2);  
        

        Scanner scanner = new Scanner(System.in); // i need to close the scanner
        
        for(int i =0;;i++) {
            statement = bots[i%bots.length].replyTo(statement);
            System.out.print(statement);
            scanner.nextLine();
        }
        
        //scanner.close();
    }   
}
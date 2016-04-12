import javax.swing.JOptionPane;

/*
 * Request handle COR!!! 
 * 
 * Chain of Responsibility !! 
 */
public abstract class StaffHandler {

	   //next element in chain or responsibility
	   StaffHandler supervisor;
       String name;
	   public void setSupervisor(StaffHandler supervisor){
	      this.supervisor= supervisor;
	   }
       public StaffHandler(String name){
    	   
    	   this.name=name;
       }

	   public abstract void handleRequest(boolean b);
		
	}


    class SupervisorHandler extends StaffHandler{

		public SupervisorHandler(String name) {
			super(name);
			
		}

		@Override
		public void handleRequest(boolean b) {
			
			// pressed endorse button :
			   if(b==true&&supervisor!=null)
			   {
				   JOptionPane.showMessageDialog(null, "Endorsed, send to next supervisor!", "Endorsed!", JOptionPane.INFORMATION_MESSAGE);
				   supervisor.handleRequest(b);
			   }
			 
			//pressed decline button : 
			   else if(b==false){
				   JOptionPane.showMessageDialog(null, "Declined !", "Declined !", JOptionPane.INFORMATION_MESSAGE);
			   }
			
		}
	
	
	
}

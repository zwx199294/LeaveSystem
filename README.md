# LeaveSystem
My Leave Application System

In my programe , there are 10 .java file .


6 for Interface ( MainInterface.java , NewStaffInterface.java, DeleteInterface.java , ManageInterface.java ,

 LeaveRequestInterface.java and HandleInterface.java ) 

 MainInterface.java has Main()  , and it contain three Button . 

 New button  ---->  NewStaffInterface.java (new staff)
 Delete button ---> DeleteInterface.java(delete staff )
 Manage button ---> ManageInterface.java 
                    ManageInterface.java ---> LeaveRequestInterface.java ( staff apply request)
                                         
					 ---> HandleInterface.java ( supervisor handle request ,call StaffHandler.java , Using chain of responsbility)
 

Staff.java file defined staff's information(level ,name ,supervisor ,account and password) , I use a List<String> to store those information for each staff . 

AddStaff.java is for update staffList . 

ParseStaffList.java has two function , 
changeSupervisor() update supervisor(one's supervisor has been delete) ; nextSupervisor() set next handler. 


StaffHandler.java use Chain of Responsibility pattern  to handle Leave request . 
 

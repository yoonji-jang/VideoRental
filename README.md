# VideoRental Refactoring List 

- 2 different Responsibilities : UI, Domain
[7bb292] : separate UI class and Domain class (VRUI -> VRLogic, ConsoleUI)

- Feature Envy, SRP violation
[04d3aa8] processing Rental object are moved from Customer class to Rental class.
[8a70f52] add getVideoLimit in Video class

- Alternative Classes with Different Interfaces (VRLogic, ConsoleUI)
[c7d5667] extract delegate for ConsoleUI : IUserInterface 

- Duplicated code
[62e92bc] add foundCustomer operation for VRLogic class
[6a7734c] add getDaysRented operation for Rental class

- Long Operation
[3d3ff03] Customer - Refine parameters of the extracted methods
package ca.mcgill.ecse321.ridesharing;

import ca.mcgill.ecse321.ridesharing.controller.RideSharingController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RidesharingApplicationTests{
@Mock
private RideSharingRepository passengerDao;
@InjectMocks
private RideSharingController controller;
private static final String PASSANGER_KEY = "TestPassenger";

private static final String NONEXSTING_KEY = "NotAPassenger";

@Test
public void contextLoads ()
{
}
}
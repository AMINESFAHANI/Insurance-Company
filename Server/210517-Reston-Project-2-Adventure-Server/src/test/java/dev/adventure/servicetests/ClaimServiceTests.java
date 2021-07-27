package dev.adventure.servicetests;


import dev.adventure.daos.claim_daos.ClaimDao;
import dev.adventure.daos.claim_daos.ClaimDaoPostgres;
import dev.adventure.entities.Claim;


import dev.adventure.exceptions.ResourceNotFound;
import dev.adventure.services.claim_services.ClaimService;
import dev.adventure.services.claim_services.ClaimServiceIMPL;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class ClaimServiceTests {
    static ClaimDao claimDao=new  ClaimDaoPostgres();
    static ClaimService claimService = new ClaimServiceIMPL(claimDao);

    static Claim claimTest1= new Claim(0,989,800,"Accident","Pending",22);
    static Claim claimTest2= new Claim(0,900,890,"Accident","Accepted",22);
    static Claim claimTest3= new Claim(0,790,600,"No Reason","Rejected",22);
    static Claim claimTest4= new Claim(0,790,600,"No Reason","Rejected",-10);
    static Claim claimTest5= new Claim(0,790,600,"No Reason","Rejected",0);






    @BeforeClass
    void setup(){
        claimTest1= claimDao.createClaim(claimTest1);
        claimTest2= claimDao.createClaim(claimTest2);
        claimTest3= claimDao.createClaim(claimTest3);


    }

    @AfterClass
    void tearDown(){
        claimTest1=null;
        claimTest2=null;
        claimTest3=null;

    }



    @Test(expectedExceptions = ResourceNotFound.class, expectedExceptionsMessageRegExp ="There are no claims in the database at this moment.")
    void testInvaGetAllClaimsById1(){claimService.getAllClaimsByUserId(0);}

    @Test(expectedExceptions = ResourceNotFound.class, expectedExceptionsMessageRegExp ="There are no claims in the database at this moment.")
    void testInvaGetAllClaimsById2(){claimService.getAllClaimsByUserId(-10);}

    @Test(expectedExceptions = ResourceNotFound.class, expectedExceptionsMessageRegExp ="There are no claims in the database at this moment.")
    void testInvaGetAllClaimsById3(){claimService.getAllClaimsByUserId(999999999);}

    @Test(expectedExceptions = ResourceNotFound.class, expectedExceptionsMessageRegExp ="There are no claims in the database at this moment.")
    void testInvaGetAllClaimsById4(){claimService.getAllClaimsByUserId(-1000);}
}

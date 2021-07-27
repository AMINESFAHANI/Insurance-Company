package dev.adventure.daotests;



import dev.adventure.daos.claim_daos.ClaimDao;
import dev.adventure.daos.claim_daos.ClaimDaoPostgres;
import dev.adventure.entities.Claim;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;



public class ClaimDaoTest {
   static ClaimDao claimDao=new  ClaimDaoPostgres();
   static Claim claimTest1= new Claim(0,989,800,"Accident","Pending",22);
   static Claim claimTest2= new Claim(0,900,890,"Accident","Accepted",22);
   static Claim claimTest3= new Claim(0,790,600,"No Reason","Rejected",22);
   static Claim claimTest4= new Claim(0,790,600,"No Reason","Rejected",-10);
   static Claim claimTest5= new Claim(0,790,600,"No Reason","Rejected",0);

   static int n= claimDao.getAllClaims().size();
   static  ArrayList<Claim> claims = new ArrayList<Claim>();

   @BeforeClass
   public static void makeSamples() {



      claimTest1= claimDao.createClaim(claimTest1);
      claimTest2= claimDao.createClaim(claimTest2);
      claimTest3= claimDao.createClaim(claimTest3);


      claims.add(0,claimTest1);
      claims.add(1,claimTest2);
      claims.add(2,claimTest3);


   }

   @AfterClass
   public static void makeSamplesNull() {
      claimTest1=null;
      claimTest2=null;
      claimTest3=null;
      claims=null;

   }
   @Test(priority = 1)
   void createClaim() {
      Assert.assertNotEquals(claimTest1.getId(),0);
      Assert.assertNotEquals(claimTest2.getId(),0);
      Assert.assertNotEquals(claimTest3.getId(),0);
      Assert.assertNotEquals(claimTest1.getId(),claimTest2.getId());
      Assert.assertNotEquals(claimTest1.getId(),claimTest3.getId());
      Assert.assertNotEquals(claimTest3.getId(),claimTest2.getId());
      Assert.assertNull(claimDao.createClaim(claimTest4));
      Assert.assertNull(claimDao.createClaim(claimTest5));
   }
   @Test(priority = 2)
   void getAllClaim() {

      int m= claimDao.getAllClaims().size();
      Assert.assertEquals(m-n,3);
      Assert.assertTrue(claims.contains(claimTest1));
      Assert.assertTrue(claims.contains(claimTest2));
      Assert.assertTrue(claims.contains(claimTest3));

      boolean test1=false;
      boolean test2=false;
      boolean test3=false;

      for (Claim claim:claimDao.getAllClaims()){

         if (claim.getId()==claimTest1.getId()){

            test1=true;
         }
         if (claim.getId()==claimTest2.getId()){

            test2=true;
         }
         if (claim.getId()==claimTest3.getId()){

            test3=true;
         }
      }
      Assert.assertTrue(test1 && test2 && test3);
      Assert.assertTrue(claimDao.getAllClaims() != null);
   }

   @Test(priority = 3)
   void getAllClaimById() {

      int m= claimDao.getAllClaimsByUserId(22).size();
      Assert.assertTrue(m>=3);
      Assert.assertTrue(claims.contains(claimTest1));
      Assert.assertTrue(claims.contains(claimTest2));
      Assert.assertTrue(claims.contains(claimTest3));

      boolean test1=false;
      boolean test2=false;
      boolean test3=false;

      for (Claim claim:claimDao.getAllClaimsByUserId(22)){

         if (claim.getId()==claimTest1.getId() && claim.getUserId()==22){

            test1=true;
         }
         if (claim.getId()==claimTest2.getId() && claim.getUserId()==22){

            test2=true;
         }
         if (claim.getId()==claimTest3.getId() && claim.getUserId()==22){

            test3=true;
         }
      }
      Assert.assertTrue(test1 && test2 && test3);

   }

   @Test(priority = 4)
   void selectClaimByID(){
      Claim selectedClaim = claimDao.getClaimByID(197);
      System.out.println(selectedClaim);
      Assert.assertEquals(selectedClaim.getId(), 197);
   }

   @Test(priority = 5)
   void updateClaim(){
      Claim updateClaim = claimDao.getClaimByID(197);
      System.out.println(updateClaim);
      updateClaim.setStatus("Accepted");
      claimDao.updateClaim(updateClaim);
      updateClaim = claimDao.getClaimByID(197);
      System.out.println(updateClaim);
      Assert.assertEquals(updateClaim.getStatus(), "Accepted");

   }


}
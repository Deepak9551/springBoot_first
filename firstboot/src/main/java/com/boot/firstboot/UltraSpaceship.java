package com.boot.firstboot;

import org.springframework.stereotype.Component;

@Component("ultraship")
public class UltraSpaceship implements SpaceshipInterface{

    private  static  String laserBeam = "laserBeam Extra";
    public  static  String blastBeam = "Blast Beam";

    @Override
    public void fire() {
        System.out.println("fire "+ " "+laserBeam );
    }
    private void blast(){
        System.out.println("Blast attack "+" "+blastBeam);
    }
}

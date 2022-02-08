import { Component, OnInit } from '@angular/core';
import { AndroidPermissions } from '@ionic-native/android-permissions/ngx';
import { LocationAccuracy } from '@ionic-native/location-accuracy/ngx';
import { Geolocation } from '@ionic-native/geolocation/ngx';
import { Platform } from '@ionic/angular';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent implements OnInit {
  dark = false;

  constructor(
    private androidPermissions: AndroidPermissions,
    private locationAccuracy: LocationAccuracy,
    private geolocation: Geolocation,
    private platform: Platform
  ) {}

  locationCoords = {
    latitude: 0,
    longitude: 0,
    accuracy: 0,
    timestamp: 0,
  };
  timetest = Date.now();

  ngOnInit(): void {
    if (localStorage.DARK) {
      this.dark = true;
    }

    if (!this.platform.is('desktop') && !this.platform.is('mobileweb')) {
      this.checkGPSPermission();
    }
  }

  changeMode(darkMode: boolean) {
    this.dark = darkMode;
    if (this.dark) {
      localStorage.setItem('DARK', 'dark');
    } else {
      localStorage.removeItem('DARK');
    }
  }

  checkGPSPermission() {
    this.androidPermissions
      .checkPermission(
        this.androidPermissions.PERMISSION.ACCESS_COARSE_LOCATION
      )
      .then(
        (result) => {
          if (result.hasPermission) {
            //If having permission show 'Turn On GPS' dialogue
            this.askToTurnOnGPS();
          } else {
            //If not having permission ask for permission
            this.requestGPSPermission();
          }
        },
        (err) => {
          alert(err);
        }
      );
  }

  requestGPSPermission() {
    this.locationAccuracy.canRequest().then((canRequest: boolean) => {
      if (canRequest) {
        console.log('4');
      } else {
        //Show 'GPS Permission Request' dialogue
        this.androidPermissions
          .requestPermission(
            this.androidPermissions.PERMISSION.ACCESS_COARSE_LOCATION
          )
          .then(
            () => {
              // call method to turn on GPS
              this.askToTurnOnGPS();
            },
            (error) => {
              //Show alert if user click on 'No Thanks'
              alert(
                'requestPermission Error requesting location permissions ' +
                  error
              );
            }
          );
      }
    });
  }

  askToTurnOnGPS() {
    this.locationAccuracy
      .request(this.locationAccuracy.REQUEST_PRIORITY_HIGH_ACCURACY)
      .then(
        () => {
          // When GPS Turned ON call method to get Accurate location coordinates
          this.getLocationCoordinates();
        },
        (error) =>
          alert(
            'Error requesting location permissions ' + JSON.stringify(error)
          )
      );
  }

  getLocationCoordinates() {
    this.geolocation
      .getCurrentPosition()
      .then((res) => {
        this.locationCoords.latitude = res.coords.latitude;
        this.locationCoords.longitude = res.coords.longitude;
        this.locationCoords.accuracy = res.coords.accuracy;
        this.locationCoords.timestamp = res.timestamp;
      })
      .catch((err) => {
        alert('Error getting location: ' + err);
      });
  }
}

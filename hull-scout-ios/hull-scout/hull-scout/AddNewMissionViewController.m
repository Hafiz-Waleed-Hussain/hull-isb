//
//  AddNewMissionViewController.m
//  hull-scout
//
//  Created by Zaki Shaheen on 2/21/15.
//  Copyright (c) 2015 Zaki Shaheen. All rights reserved.
//

#import "AddNewMissionViewController.h"
#import <MapKit/MapKit.h>
#import <Parse/Parse.h>
#import "AKPickerView.h"

@interface AddNewMissionViewController ()
@property (weak, nonatomic) IBOutlet MKMapView *mapView;
@property (weak, nonatomic) IBOutlet UITextField *titleField;

@property (weak, nonatomic) IBOutlet AKPickerView *picker;

@property (strong, nonatomic) NSArray *categories;

@end

@implementation AddNewMissionViewController


- (NSUInteger)numberOfItemsInPickerView:(AKPickerView *)pickerView{
    return [[self categories] count];
}

- (NSString *)pickerView:(AKPickerView *)pickerView titleForItem:(NSInteger)item{
    return [self.categories objectAtIndex:item];
    
}
- (IBAction)createMission:(id)sender {
    PFObject *newMission  = [[PFObject alloc] initWithClassName:@"Mission"];
    newMission[@"title"] = self.titleField.text;
    newMission[@"category"]= [self.categories objectAtIndex:self.picker.selectedItem];
    newMission[@"user"] = [PFUser currentUser];
    CLLocationCoordinate2D loc = self.mapView.userLocation.location.coordinate;
    
    newMission[@"lat"] = @(loc.latitude);
    newMission[@"lon"] = @(loc.longitude);
    
    [newMission saveInBackgroundWithBlock:^(BOOL succeeded, NSError *error) {
        if(succeeded){
            //move on to share screen., pass on the PFObject
            [self performSegueWithIdentifier:@"shareMission" sender:self];
            
        }else{
            //show error dialog
        }
    }];
    
    
    
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    self.picker.dataSource = self;
    self.picker.delegate = self;
    
    self.picker.font = [UIFont fontWithName:@"HelveticaNeue-Light" size:20];
    self.picker.highlightedFont = [UIFont fontWithName:@"HelveticaNeue" size:20];
    self.picker.interitemSpacing = 20.0;
    self.picker.fisheyeFactor = 0.001;
    self.picker.pickerViewStyle = AKPickerViewStyle3D;
    self.picker.maskDisabled = false;

    
    currentUser = [PFUser currentUser];
    
    self.categories = @[@"Environment",
                        @"Health",
                        @"Sanitation",
                        @"Transporation",
                        @"Safety"
                        ];
    
    
    locationManager = [[CLLocationManager alloc] init];
    locationManager.delegate = self;
    locationManager.distanceFilter = kCLDistanceFilterNone; //whenever we move
    locationManager.desiredAccuracy = kCLLocationAccuracyBest;
    
    [locationManager startUpdatingLocation];
    [locationManager requestWhenInUseAuthorization]; // Add This Line
    
    
    
    UIBarButtonItem *right = [UIBarButtonItem new];
    right.title = @"Next";
    [right setTarget:self];
    [right setAction:@selector(createMission:)];
    
    [self.navigationItem setRightBarButtonItem:right];
}

- (void)locationManager:(CLLocationManager *)manager didChangeAuthorizationStatus:(CLAuthorizationStatus)status {
        self.mapView.showsUserLocation = YES;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end

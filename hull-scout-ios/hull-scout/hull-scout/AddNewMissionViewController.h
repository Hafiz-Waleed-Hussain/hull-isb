//
//  AddNewMissionViewController.h
//  hull-scout
//
//  Created by Zaki Shaheen on 2/21/15.
//  Copyright (c) 2015 Zaki Shaheen. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Parse/Parse.h>
#import "AKPickerView.h"
@interface AddNewMissionViewController : UIViewController<AKPickerViewDataSource, AKPickerViewDelegate, CLLocationManagerDelegate> {
    PFUser *currentUser;
    CLLocationManager *locationManager;
    
}

@end

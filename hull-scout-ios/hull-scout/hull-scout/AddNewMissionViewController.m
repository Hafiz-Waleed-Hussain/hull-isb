//
//  AddNewMissionViewController.m
//  hull-scout
//
//  Created by Zaki Shaheen on 2/21/15.
//  Copyright (c) 2015 Zaki Shaheen. All rights reserved.
//

#import "AddNewMissionViewController.h"
#import <MapKit/MapKit.h>

@interface AddNewMissionViewController ()
@property (weak, nonatomic) IBOutlet MKMapView *mapView;
@property (weak, nonatomic) IBOutlet UITextField *title;
@property (weak, nonatomic) IBOutlet UIView *categoryView;

@end

@implementation AddNewMissionViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end

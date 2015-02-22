//
//  ShareMissionViewController.m
//  hull-scout
//
//  Created by Zaki Shaheen on 2/22/15.
//  Copyright (c) 2015 Zaki Shaheen. All rights reserved.
//

#import "ShareMissionViewController.h"
#import "AdvertiseCollabViewController.h"
#import <FacebookSDK/FacebookSDK.h>
@interface ShareMissionViewController ()

@end

@implementation ShareMissionViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)share{
    // Check if the Facebook app is installed and we can present the share dialog
    FBLinkShareParams *params = [[FBLinkShareParams alloc] init];
    params.link = [NSURL URLWithString:@"http://hullforislamabad.launchrock.com"];
    
    // If the Facebook app is installed and we can present the share dialog
    if ([FBDialogs canPresentShareDialogWithParams:params]) {
        // Present the share dialog
        
        [FBDialogs presentShareDialogWithLink:params.link
                                      handler:^(FBAppCall *call, NSDictionary *results, NSError *error) {
                                          if(error) {
                                              // An error occurred, we need to handle the error
                                              // See: https://developers.facebook.com/docs/ios/errors
                                              NSLog(@"Error publishing story: %@", error.description);
                                          } else {
                                              // Success

                                              [self performSegueWithIdentifier:@"advertiseCollab" sender:self];
                                          }
                                      }];
        
    } else {
        // Present the feed dialog
    }
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if ([segue.identifier isEqualToString:@"advertiseCollab"]) {

        AdvertiseCollabViewController *destViewController = segue.destinationViewController;
        destViewController.parentNav = self.navigationController;

    }
}
- (IBAction)sharePressed:(id)sender {
    [self share];
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

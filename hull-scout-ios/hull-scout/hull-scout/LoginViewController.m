//
//  LoginViewController.m
//  hull-scout
//
//  Created by Zaki Shaheen on 2/21/15.
//  Copyright (c) 2015 Zaki Shaheen. All rights reserved.
//

#import "LoginViewController.h"
#import <Parse/Parse.h>
#import <ParseFacebookUtils/PFFacebookUtils.h>
@interface LoginViewController ()

@end

@implementation LoginViewController

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

- (IBAction)loginFacebook:(id)sender {
    [PFFacebookUtils initializeFacebook];
    
    NSArray *permissions = @[@"public_profile", @"email", @"user_friends"];

    [PFFacebookUtils logInWithPermissions:permissions block:^(PFUser *user, NSError *error) {
        if (!user) {
            NSLog(@"Uh oh. The user cancelled the Facebook login.");
        } else if (user.isNew) {
            
            //get name, email, set XP to zero, level to zero.
            
            [FBRequestConnection startForMeWithCompletionHandler:^(FBRequestConnection *connection, id result, NSError *error) {
                if (!error) {
                    
                    //setup a new user with required infos.
                    
                    user[@"email"] = result[@"email"];
                    user[@"name"] = result[@"name"];
                    user[@"gender"] = result[@"gender"];
                    user[@"xp"] = @(0);
                    user[@"level"] = @(0);
                    
                    NSLog(@"user info: %@", result);
                    [user saveInBackgroundWithBlock:^(BOOL succeeded, NSError *error) {
                        if(succeeded){
                            
                            NSUserDefaults *def = [NSUserDefaults standardUserDefaults];
                            [def setObject:@(YES) forKey:@"loggedIn"];
                            
                            [self goToProfile];
                            
                        }else{
                            NSLog(@"Could not publish user to hull");
                        }
                    }];
                    
                } else {
                    NSLog(@"Problem getting user info");
                }
            }];

            
        } else {
            [self goToProfile];
        }
    }];
}

- (void) goToProfile{
    [self performSegueWithIdentifier:@"toProfile" sender:self];
}
@end

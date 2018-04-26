//
//  ViewController.swift
//  Shelter Finder
//
//  Created by Suin Yun on 4/25/18.
//  Copyright © 2018 Suin Yun. All rights reserved.
//

import UIKit
import FirebaseAuth

class ViewController: UIViewController {
    
    
    @IBOutlet weak var signinSelector: UISegmentedControl!
    
    
    @IBOutlet weak var signinLabel: UILabel!
    
    @IBOutlet weak var emailTextfield: UITextField!
    
    @IBOutlet weak var passwordTextfield: UITextField!
    
    @IBOutlet weak var signinButton: UIButton!
    
    var isSignIn:Bool = true
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func signinSelectorChanged(_ sender: UISegmentedControl) {
        // flip the boolean
        isSignIn = !isSignIn
        
        //check the boolean and set the button and labels
        if isSignIn {
            signinLabel.text = "Sign in"
            signinButton.setTitle("Sign in" , for: .normal)
        }
        else {
            signinLabel.text = "Register"
            signinButton.setTitle("Register" , for: .normal)
        }
    }
    
    
    
    @IBAction func signinButtonTapped(_ sender: UIButton) {
        if let email = emailTextfield.text, let pass = passwordTextfield.text {
            //check if sign in or register
            if isSignIn {
                //signin the user with firebase
                Auth.auth().signIn(withEmail: email, password: pass, completion: { (user, error) in
                    //check that user isnt nil
                    if let u = user {
                        //user is found, go to homescreen
                        self.performSegue(withIdentifier: "goHome", sender: self)
                    }
                    else {
                        //error, check error and show msg
                    }
                })
            }
            else {
                //register the user with firebase
                Auth.auth().createUser(withEmail: email, password: pass, completion: { (user, error) in
                    //check that user isnt nil
                    if let u = user {
                        //user is found, go to homescreen
                        self.performSegue(withIdentifier: "goHome", sender: self)
                    }
                    else {
                        //error, check error and show msg
                    }
                })
            }
        }
    }
    
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        //dismisses the keyboard when the view is tapped on
        emailTextfield.resignFirstResponder()
        passwordTextfield.resignFirstResponder()
    }
    
}


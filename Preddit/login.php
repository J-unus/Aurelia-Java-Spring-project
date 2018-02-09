<?php
// Include config file
require_once 'config.php';
 
// Define variables and initialize with empty values
$username = $password = "";
$username_err = $password_err = "";
 
// Processing form data when form is submitted
if($_SERVER["REQUEST_METHOD"] == "POST"){
 
    // Check if username is empty
    if(empty(trim($_POST["username"]))){
        $username_err = 'Please enter username.';
    } else{
        $username = trim($_POST["username"]);
    }
    
    // Check if password is empty
    if(empty(trim($_POST['password']))){
        $password_err = 'Please enter your password.';
    } else{
        $password = trim($_POST['password']);
    }
    
    // Validate credentials
    if(empty($username_err) && empty($password_err)){
        // Prepare a select statement
        $sql = "SELECT username, password FROM t134570v1_users WHERE username = ?";
        
        if($stmt = mysqli_prepare($link, $sql)){
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "s", $param_username);
            
            // Set parameters
            $param_username = $username;
            
            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                // Store result
                mysqli_stmt_store_result($stmt);
                
                // Check if username exists, if yes then verify password
                if(mysqli_stmt_num_rows($stmt) == 1){                    
                    // Bind result variables
                    mysqli_stmt_bind_result($stmt, $username, $hashed_password);
                    if(mysqli_stmt_fetch($stmt)){
                        if(password_verify($password, $hashed_password)){
                            /* Password is correct, so start a new session and
                            save the username to the session */
                            session_start();
                            $_SESSION['username'] = $username;      
                            header("location: welcome.php");
                        } else{
                            // Display an error message if password is not valid
                            $password_err = 'The password you entered was not valid.';
                        }
                    }
                } else{
                    // Display an error message if username doesn't exist
                    $username_err = 'No account found with that username.';
                }
            } else{
                echo "Oops! Something went wrong. Please try again later.";
            }
        }
        
        // Close statement
        mysqli_stmt_close($stmt);
    }
    
    // Close connection
    mysqli_close($link);
}
?>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
        <link rel="stylesheet" href="style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style type="text/css">
            body {
                font: 14px sans-serif;
            }
            
            .wrapper {
                width: 350px;
                padding: 20px;
            }

        </style>
    </head>

    <body>
        <div class="wrapper" style="margin:auto;">
            <h2>Login</h2>
            <form action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']); ?>" method="post">
                <div class="form-group <?php echo (!empty($username_err)) ? 'has-error' : ''; ?>">
                    <label>Username:<sup>*</sup></label>
                    <input type="text" name="username" class="form-control" value="<?php echo $username; ?>">
                    <span class="help-block"><?php echo $username_err; ?></span>
                </div>
                <div class="form-group <?php echo (!empty($password_err)) ? 'has-error' : ''; ?>">
                    <label>Password:<sup>*</sup></label>
                    <input type="password" name="password" class="form-control">
                    <span class="help-block"><?php echo $password_err; ?></span>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-primary" value="Submit">
                </div>
                <p>Don't have an account? <a href="register.php">Sign up now</a>.</p>
            </form>
        </div>
        <?php
        
        function addcond($con,$fld) {
  global $q, $c;

  if (isset($_REQUEST[$fld])  && $_REQUEST[$fld]!="") {
    if ($c!="") $c=$c . " and ";
    $c=$c . " $fld='" . mysqli_real_escape_string($con,$_REQUEST[$fld]) ."' ";
  }
}

function main() {
    global $q, $c;
    $con = mysqli_connect("localhost","st2014","progress","st2014");
    if (!$con) {
        die('Could not connect: ' . mysqli_error());
    }

    $y="SELECT * from t134570_news ORDER BY Points DESC";

    addcond($con,"username");
    addcond($con,"password");    
  
    $result = mysqli_query($con,$y); ?>
            <div class="container">
                <?php while($row = mysqli_fetch_array($result)) : ?>
                <?php
              $upvotes = "SELECT * FROM t134570_voted WHERE Postid = " . $row['id'] . " AND Votevalue = 1;";
              $downvotes = "SELECT * FROM t134570_voted WHERE Postid = " . $row['id'] . " AND Votevalue = -1;";
              $upvotes_result = mysqli_query($con, $upvotes);
              $downvotes_result = mysqli_query($con, $downvotes);
  
              $upvotes_result1 = mysqli_num_rows($upvotes_result);

              $downvotes_result1 = mysqli_num_rows($downvotes_result);
  
              $points = $upvotes_result1 - $downvotes_result1;
    
                $updateNews = "UPDATE t134570_news SET Points='". $points ."' WHERE id='" . $row['id'] . "';";
                $updateNews_result = mysqli_query($con, $updateNews);

            ?>


                    <div class="panel panel-default panel-default">
                        <div class="panel-body">
                            <div class="pull-right">
                                <span class="badge badge-info"><?php echo $row['adder'] ?></span>
                                <span class="badge badge-success points"><?php echo $points; ?></span>
                            </div>
                            <span><?php echo $row['txt']; ?></span>
                        </div>
                    </div>
                    <?php endwhile; ?>
            </div>

            <?php mysqli_close($con); } main(); ?>
    </body>

    </html>

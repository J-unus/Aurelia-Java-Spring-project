<?php
// Initialize the session
session_start();
 
// If session variable is not set it will redirect to login page
if(!isset($_SESSION['username']) || empty($_SESSION['username'])){
  header("location: login.php");
  exit;
}

function vote($value, $postid) {

  $con = mysqli_connect("localhost","st2014","progress","st2014");
  
  if (!$con) {
    die('Could not connect: ' . mysqli_connect_error());
  }
  
  $userid = $_SESSION['username'];
  $user_votes = "DELETE FROM t134570_voted WHERE Voter = '" . $userid . "' AND Postid=" . $postid ;
  
  $user_votes_result = mysqli_query($con,$user_votes);
  $sql="INSERT INTO t134570_voted (Postid, Voter, Votevalue) VALUES('$postid','$userid', '$value');";

  $result = mysqli_query($con,$sql);
  
  if (!$result) {
    echo "<div style='text-align: center;'>Error: " . $sql . "<br>" . mysqli_error($con) . "</div>";
  }
  if (!$user_votes_result) {
    echo "<div style='text-align: center;'>Error: " . $user_votes . "<br>" . mysqli_error($con) . "</div>";
  }
  
  mysqli_close($con);
}
if (isset($_REQUEST['upVote']) && $_REQUEST['upVote']=='submit' && isset($_REQUEST['postid'])) {
    vote(1, $_REQUEST['postid']);
}elseif(isset($_REQUEST['downVote']) && $_REQUEST['downVote']=='submit' && isset($_REQUEST['postid'])){
    vote(-1, $_REQUEST['postid']);
}



function submitPost() {
  if (isset($_REQUEST['content'])) {
      $content=$_REQUEST['content'];
  } else {
    $description="";
  }

  $con = mysqli_connect("localhost","st2014","progress","st2014");
  
  if (!$con) {
    die('Could not connect: ' . mysqli_connect_error());
  }
  
  $content=mysqli_real_escape_string($con,$content);
  $userid = $_SESSION['username'];
  $sql="INSERT INTO t134570_news (txt, adder) VALUES('$content','$userid');";
  $result = mysqli_query($con,$sql);

  if (!$result) {
    echo "<div style='text-align: center;'>Error: " . $sql . "<br>" . mysqli_error($con) . "</div>";
  }
  
  mysqli_close($con);
}
        
if (isset($_REQUEST['submitPost']) && $_REQUEST['submitPost']=='submit') {
  submitPost();
}

    ?>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>Welcome</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
        <link rel="stylesheet" href="style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://unpkg.com/isotope-layout@3/dist/isotope.pkgd.js"></script>
        <script src="script.js"></script>

        <style type="text/css">
            body {
                font: 14px sans-serif;
                text-align: center;
            }

        </style>
    </head>

    <body>
        <div class="page-header">
            <h1>Hi, <b><?php echo $_SESSION['username']; ?></b>. Welcome to Preddit.</h1>
        </div>
        <p><a href="logout.php" class="btn btn-danger">Sign Out</a></p>

        <form method="post" enctype="multipart/form-data">

            <input type="hidden" name="submitPost" value="submit">
            <div class="form-group">
                <label for="Description">Post:</label>
                <input type="text" class="form-control" name="content" id="Description" placeholder="Description" required>
            </div>
            <button style="margin-bottom:10px;" type="submit" class="btn btn-primary">Submit</button>
        </form>

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

              $user_vote = "SELECT * FROM t134570_voted WHERE Voter = '" . $_SESSION['username']."'";                                         $user_vote_result = mysqli_query($con, $user_vote);
  
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
                                <?php if ( $row['adder'] != $_SESSION['username'] ) : ?>
                                <form method="post" enctype="multipart/form-data">
                                    <input type="hidden" name="postid" value="<?php echo $row['id'];?>">
                                    <input type="hidden" name="upVote" value="submit">
                                    <button name="vote" type="submit" class="btn btn-success btn-md">Upvote</button>
                                </form>
                                <form method="post" enctype="multipart/form-data">
                                    <input type="hidden" name="postid" value="<?php echo $row['id'];?>">
                                    <input type="hidden" name="downVote" value="submit">
                                    <button name="vote" type="submit" class="btn btn-danger btn-md">Downvote</button>
                                </form>
                                <?php endif; ?>
                            </div>
                            <span><?php echo $row['txt']; ?></span>
                        </div>
                    </div>
                    <?php endwhile; ?>
            </div>

            <?php mysqli_close($con); } main(); ?>

    </body>

    </html>

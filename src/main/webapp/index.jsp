<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <!-- Đường dẫn tới các tệp CSS của Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">Log in</div>
                <div class="card-body">
                    <form action="dash_board" method="post">
                        <div class="form-group">
                            <label for="username">User name:</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <br>
                        <input type="submit" class="btn btn-primary" value="Login"></input>
                        <input type="hidden" name="action" value="login">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Đường dẫn tới các tệp JavaScript của Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>

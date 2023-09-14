<h1>Add role</h1>
<div class="col-md-12">

    <form action="dash_board" method="post">
        <div class="form-group">
            <label for="rolename">Role name:</label>
            <input type="text" class ="form-control" id="rolename" name="rolename" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" class ="form-control" id="description" name="description" required>
        </div>

        <div class="form-group">
            <label>Status:</label>
            <select class="form-select" aria-label="Default select example" name="status">
                <option value="0">DEACTIVE</option>
                <option value="1">ACTIVE</option>
                <option value="-1">DELETE</option>
            </select>
        </div>

        <br>
        <input type="submit" class="btn btn-primary" value="Add role"></input>
        <input type="hidden" name="action" value="add_role">
    </form>
</div>
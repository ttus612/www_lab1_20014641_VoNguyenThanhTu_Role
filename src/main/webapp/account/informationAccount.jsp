    <h3>Information Account</h3>

    <div class="mb-3 row">
        <label for="staticFullName" class="col-sm-2 col-form-label">Full Name:</label>
        <div class="col-sm-10">
            <input type="text" readonly class="form-control-plaintext" id="staticFullName" value=${session_name.getFull_name()}>
        </div>
    </div>
    <div class="mb-3 row">
        <label for="staticPassword" class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-10">
            <input type="text" readonly class="form-control-plaintext" id="staticPassword" value= ${session_name.getPassword()}>
        </div>
    </div>
    <div class="mb-3 row">
        <label for="staticEmail" class="col-sm-2 col-form-label">Email:</label>
        <div class="col-sm-10">
            <input type="text" readonly class="form-control-plaintext" id="staticEmail" value=${session_name.getEmail()}>
        </div>
    </div>
    <div class="mb-3 row">
        <label for="staticPhone" class="col-sm-2 col-form-label">Phone:</label>
        <div class="col-sm-10">
            <input type="text" readonly class="form-control-plaintext" id="staticPhone" value=${session_name.getPhone()}>
        </div>
    </div>
    <div class="mb-3 row">
        <label for="staticStatus" class="col-sm-2 col-form-label">Status:</label>
        <div class="col-sm-10">
            <input type="text" readonly class="form-control-plaintext" id="staticStatus" value=${session_name.getStatus()}>
        </div>
    </div>



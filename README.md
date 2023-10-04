<img align="left" width="400" src="https://github.githubassets.com/images/modules/profile/profile-first-repo.svg">
<h1 align="center">Hi 👋, I'm Vo Nguyen Thanh Tu</h1>

- ✍ I'm a student of: [Industrial University of Ho Chi Minh City](https://iuh.edu.vn/).
- ✍ Subject : WWW với Công nghệ java
- ✍ Content: Nội dung bài tập tuần 1

<br />

# 📒Hoạt độnng:
<table style="width:100%;">
  <tr>
    <td>
      <img src="https://github-readme-stats.vercel.app/api/top-langs/?username=ttus612&bg_color=FFFFFF00&text_color=179fa3&layout=compact&hide=CSS&langs_count=10&custom_title=Top%20ngôn%20ngữ%20được%20dùng" alt="ttus612" width="100%"/>
      <img src="https://github-readme-stats.vercel.app/api?username=ttus612&bg_color=FFFFFF00&text_color=179fa3&show_icons=true&count_private=true&include_all_commits=true&custom_title=Hoạt%20động%20trên%20Github" alt="ttus612" width="100%"/>
    </td>
    <td>
      <p align="center"> 
        <img src="https://cdn.dribbble.com/users/1059583/screenshots/4171367/coding-freak.gif" alt="dev" width="100%"/>
      </p>
    </td>
  </tr>
</table>

# 📒Kĩ năng áp dụng trong Project :
<p align="center">
  <img src="https://img.icons8.com/color/48/000000/mysql-logo.png"/>
  <img width="48" height="48" src="https://img.icons8.com/color/48/maria-db.png" alt="maria-db"/>
  <img width="48" height="48" src="https://img.icons8.com/color/48/java-coffee-cup-logo--v1.png" alt="java-coffee-cup-logo--v1"/>
  <img width="48" height="48" src="https://img.icons8.com/color/48/web.png" alt="web"/>
</p>

# 📒Yêu cầu Project :
<p align="center">
  <img src="images/yeuCau.jpg" width = 80%/>
</p>

# 😍Trình bày về project:
## 📚Tạo một servlet có tên ControlServlet (partern cùng tên). Servlet này nhận một tham số (parameter) có tên là action.
- ✍Em đã tạo ra một servlet có tên ControlServle sử dụng cho **doGet** và **doPost**
<table style="width:100%;">
  <tr>
    <td>
      <img src="images/taoServlet_get.jpg" alt="ttus612" width="100%"/>
    <td>
      <img src="images/taoServlet_get.jpg" alt="ttus612" width="100%"/>
  </tr>
</table>

## 📚 Một trang html hiển thị cửa sổ đăng nhập. Nếu đăng nhập thành công và là quyền admin thì hiển thị trang dashboard cho phép quản lý các account khác (bao gồm các quyền thêm, xóa, sửa và cấp quyền). Còn không (không phải admin) thì hiển thị thông tin của người đăng nhập.
### 🧑‍💻Admin
 1. ✍Login: Đăng nhập với tài khoản "nguyenvanb@gmail.com" , password "123" là tài khoản admin
  <p align="center">
    <p>Login</p>
    <img src="images/admin/login_admin.jpg" width = 100%/
  </p>
2. ✍Dasboard admin: Khi đăng nhập thành công thì thanh menu đầy đủ các chức năng khác của 1 admin mà yêu cầu đưa ra như: quản lí account, log, ...
  <p align="center">
    <img src="images/admin/navbar_admin.jpg" width = 100%/>
  </p>
3. ✍Cấp quyền cho một account: Khi thêm thì admin có thẻ chọn 1 hoặc nhiều role cho 1 account
  <p align="center">
    <img src="images/admin/add_account.jpg" width = 100%/>
  </p>
4. ✍Hiển thị các quyền của một account: Khi chọn chức năng này nó sẽ hiển thị lên danh sách tất cả các quyền của một account được cấp quyền, nếu ta muốn lọc theo account nào đó ta chỉ cần chọn giá trị trong select và nhấn nút là nó sẽ lấy ra tất cả các quyền mà account đó sở hữu
  <p align="center">
    <img src="images/admin/quyen_cua_1_account.jpg" width = 100%/>
    <img src="images/admin/quyen_cua_1_account_when_filter.jpg" width = 100%/>
  </p>
5. ✍Hiển thị các account của một role: Khi chọn chức năng này nó sẽ hiển thị lên danh sách tất cả các account của role, nếu ta muốn lọc theo role nào đó ta chỉ cần chọn giá trị trong select và nhấn nút là nó sẽ lấy ra tất cả các account tương ứng với role đó
 <p align="center">
    <img src="images/admin/account_cua_1_role.jpg" width = 100%/>
    <img src="images/admin/account_cua_1_role_when_filter.jpg" width = 100%/>
  </p>

  6. ✍Các chức năng CRUD và lấy danh sách(Theo em trong đây chỉ có phầnt role và phần account thì chỉ cần thôi ạ, Phần log thì nó sẽ được tự động cập nhật)
 <p align="center">
    <img src="images/admin/add_account.jpg" width = 100%/>
    <img src="images/admin/list_account.jpg" width = 100%/>
    <img src="images/admin/add_role.jpg" width = 100%/>
    <img src="images/admin/edit_role.jpg" width = 100%/>
    <p>Khi xóa sẽ chuyển sang trạng thái diasable chứ không xóa ra khỏi danh sách</p>
    <img src="images/admin/xoa_role.jpg" width = 100%/>

  </p>

### 👨‍🦱Không phải là admin:
  1. ✍Login: Đăng nhập với tài khoản "nguyenvana@gmail.com" , password "123" không phải là tài khoản admin
  <p align="center">
    <p>Login</p>
    <img src="images/user/login_user.jpg" width = 100%/>
    <p>Login success</p>
    <img src="images/user/login_user_thanhcong.jpg" width = 100%/>
  </p>
  2. ✍Dasboard không phải admin: Khi đăng nhập thành công thì thanh menu của phần user chỉ có thể xem được thông tin của họ và không có các chức năng khác của 1 admin
  <p align="center">
    <img src="images/user/navbar_user.jpg" width = 100%/>
    <img src="images/user/thong_tin_user.jpg" width = 100%/>
  </p>
  
### 🖊️Ghi Log:
Khi user hoặc admin login vào hệ thống thì hệ thống sẽ tự động ghi lại Log mà người đó đã đăng nhập vào hệ thống, Khi đăng xuất thì hệ thống sẽ cập nhật lại thời gian logout còn nếu chưa nó sẽ mặc định là null
  <p align="center">
    <img src="images/admin/ghi_log.jpg" width = 100%/>
    <img src="images/admin/ghi_log_2.jpg" width = 100%/>
  </p>

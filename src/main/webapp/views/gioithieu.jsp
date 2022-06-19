<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Shop</title>
    <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gioithieu.css">
    <link rel="icon" href="${pageContext.request.contextPath}/ìmg/logo.png" sizes="32x32">
</head>
<body>
<%@ include file="header.jsp"%>
<main class="phan-than">
    <div class="gioi-thieu-con">
        <div class="phan-dau-gioi-thieu">
            <h1>Giới thiệu</h1>
        </div>
        <div class="noi-dung">
            <p>Từ một cộng đồng của những người đam mê viết và chia sẻ kiến thức, Spiderum đã không ngừng phát triển và
                cho ra mắt bộ ba cuốn sách tập hợp bài viết của nhiều tác giả dành cho người trẻ: <strong>Dăm ba cái tuổi
                    trẻ, Du học kí: Vạn dặm có chi? và Người trong muôn nghề và Người trong muôn nghề: Ngành IT có
                    gì?</strong></p>
            <p>Kết tinh từ góc nhìn của chính những người trẻ đầy bản lĩnh giữa muôn vàn môi trường sống, mỗi sản phẩm của
                Spiderum lại phản ánh một vấn đề nổi bật trong xã hội một cách toàn diện nhưng không kém phần gai góc khiến
                nhiều bạn trẻ khác ngoài kia đang cùng trăn trở: Mình nên đọc gì để làm phong phú tâm hồn, có những kĩ năng
                cần thiết và hiểu được rằng thế giới ngoài kia vận hành như thế nào.</p>
            <p>Thấu hiểu được những trăn trở đó, cửa hàng của Spiderum được ra đời để đồng hành, giới thiệu đến các bạn
                những cuốn sách đầy tâm huyết của đội ngũ Spiderum cùng nhiều dự định chúng tôi đang ấp ủ trong tương lai.
                Bên cạnh đó, rất nhiều những cuốn sách bổ ích khác đến từ bất cứ thể loại nào cũng sẽ góp mặt tại đây qua
                các chuyên mục review, chia sẻ sách hay. Chúng tôi mong muốn sẽ tạo ra một nền tảng tương tác thân thiện,
                gần gũi và hữu dụng, gắn kết những tâm hồn yêu sách đang không ngừng tìm tòi, học hỏi để phát triển bản
                thân.</p>
            <p>Hãy cùng Nhện Book thám hiểm thế giới sách và lan tỏa văn hóa đọc nhé! Các bạn có thể tìm hiểu thêm thông
                tin chi tiết về dòng sách của Spiderum tại đây: </p>
            <p>1. Dăm Ba Cái Tuổi Trẻ </p>
            <p>2. Du học kí: Vạn dặm có chi? </p>
            <p>3. Người Trong Muôn Nghề</p>
        </div>
    </div>
</main>
<%@ include file="footer.jsp"%>
<script src="../js/header.js"></script>
</body>

<script>
    function dangXuat(){
        fetch('/api/dang-xuat', {
            method: 'POST',
        })
            .then(response => {
                if (response.status !== 200 && response.status !== 201) {
                    throw response
                }
                return response.json()
            })
            .then(data => {
                location.assign('/trang-chu');
            })
            .catch(async function(err) {
                alert("Lỗi !");
            })
    }
</script>
</body>
</html>
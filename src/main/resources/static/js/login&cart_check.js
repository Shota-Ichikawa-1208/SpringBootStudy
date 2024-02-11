 function updateContent() {
                console.log("Updating content...");

                var contentDiv = document.getElementById('cart_null');
				var mainDiv = document.getElementById('main_div');
               

                if (cart_js !== null && cart_js !== 'null') {
                    console.log("User is logged in.");
                    
                    contentDiv.innerHTML ='<p>カート内は空です</p>';
                    mainDiv.parentNode.removeChild(mainDiv);
                } 
            }

            document.addEventListener('DOMContentLoaded', updateContent);
            
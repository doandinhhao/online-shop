   

import React from 'react';
import Typography from '@mui/material/Typography';
import Snackbar from '@mui/material/Snackbar';
import MuiAlert, { AlertProps } from '@mui/material/Alert';
import { Box, IconButton } from '@mui/material';
import { addProductToCart, getProductPhoto } from '../../../api/ProductRequest';
import { useAppSelector } from '../../../hooks';
import { useNavigate } from 'react-router-dom';
import { getTranslation } from '../../../../i18n/i18n';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import NoPhoto from "../../../../img/no_photo.jpg";

const Alert = React.forwardRef<HTMLDivElement, AlertProps>(function Alert(
  props,
  ref,
) {
  return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

interface CategoryProp {
  id: number,
  name: string
}

interface ProductCardProps {
  id: number,
  title: string,
  price: string,
  categories: CategoryProp[],
  description: string,
}

const ProductCard = ({ id, title, price, categories, description }: ProductCardProps) => {
  const navigate = useNavigate();

  const roles = useAppSelector(state => state.info.info.roles);
  const lang = useAppSelector(state => state.lang.lang);
  const [isAdded, setIsAdded] = React.useState<boolean>(false);
  const [photo, setPhoto] = React.useState(NoPhoto);

  const jwt = useAppSelector(state => state.jwt);

  const handleAlertClick = () => {
    setIsAdded(false);
  };

  const handleAddProduct = async () => {
    setIsAdded(false);

    const response = await addProductToCart(jwt.token, id, 1);

    if (response.status == 200) {
      setIsAdded(true);
    }
  }

  const handleEditProduct = async () => {
    // @ts-ignore 
    if (roles[0].name == "USER") {
      return;
    }

    navigate("/product/edit", {
      state: {
        id: id,
        title: title,
        categories: categories,
        price: price,
        description: description
      }
    })
  }

  React.useEffect(() => {
    const getProductPhotoRequest = async () => {
      const photoBlob = await getProductPhoto(id);

      if (photoBlob.size > 0) {
        setPhoto(URL.createObjectURL(photoBlob));
      }
    }

    getProductPhotoRequest();
  }, []);

  return (
    <>
      {isAdded &&
        <Snackbar open={isAdded} autoHideDuration={2000} onClose={handleAlertClick}>
          <Alert onClose={handleAlertClick} id="alert-success" severity="success" sx={{ width: '100%' }}>
            {getTranslation(lang, "product_added_to_cart_successfully")}
          </Alert>
        </Snackbar>}
      <div onClick={handleEditProduct} className={roles[0].name == "SELLER" ? "my-product-card" : ""}>
        <Box sx={{ display: "flex", flexDirection: "column", }} id={title + '-' + id}>
          <Box height={"20vh"} width={"32vh"}>
            <img width={"100%"} height={"100%"} src={photo} data-testid={"card-image-" + id} alt="product-photo" style={{ objectFit: "cover", borderRadius: "15px" }} />
            {// @ts-ignore 
              roles[0].name == "USER" ? (
                <IconButton color="primary" aria-label="add to shopping cart" onClick={handleAddProduct} id={"add-product-" + id} sx={{ marginLeft: "83%", border: "1px solid", width: "15%", height: "25%" }}>
                  {/* NOSONAR: Function addProduct doesn't return Promise.*/}
                  <AddShoppingCartIcon />
                </IconButton>) : null
            }
          </Box>
          <Box margin="0 5%" width="22vh">
            <Typography variant="body2" fontSize={10} sx={{ marginTop: "3%", lineHeight: "1", overflow: "hidden", overflowWrap: "break-word" }}>{categories && categories.length > 0 ? categories[0].name : getTranslation(lang, "no_category")}</Typography>
            <Typography variant="body1" sx={{ marginTop: "2.5%", height: "3.3vh", lineHeight: "1", overflow: "hidden", overflowWrap: "break-word" }}>{title}</Typography>
            <Typography variant="body1" fontWeight={800} sx={{ marginTop: "2.5%", height: "3vh", lineHeight: "1", overflow: "hidden", overflowWrap: "break-word" }}>{price + "€"}</Typography>
          </Box>
        </Box>
      </div>
    </>
  );
}

export default ProductCard;
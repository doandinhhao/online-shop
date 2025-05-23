   

import * as React from 'react';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';

import Copyright from '../../footer/Copyright';
import Header from '../../Header';
import { getSellerProducts } from '../../../api/ProductRequest';
import Product from './Product';
import SellIcon from '@mui/icons-material/Sell';

import { useAppSelector } from '../../../hooks';
import { useNavigate } from 'react-router-dom';
import { getTranslation } from '../../../../i18n/i18n';
import MyProductCard from './MyProductCard';
import { Paper } from '@mui/material';

const ProductsContainer = () => {
  const jwt = useAppSelector(state => state.jwt);
  const roles = useAppSelector(state => state.info.info.roles);
  const lang = useAppSelector(state => state.lang.lang);
  const username = useAppSelector(state => state.username.sub);

  const [products, setProducts] = React.useState<Product[]>([]);
  const navigate = useNavigate();

  React.useEffect(() => {
    const token = jwt.token;

    const fetchProducts = async () => {
      const productsResponse = await getSellerProducts(token, username);

      if (productsResponse.status) {
        if (productsResponse.status == 401) {
          navigate("/login");
        }
      }

      setProducts(productsResponse);
    }

    fetchProducts(); // NOSONAR: It should not await.
  }, []);

  return (
    <Container component="main" maxWidth={false} id="my-products-container" sx={{ height: "100vh" }} disableGutters>
      <Header />
      <Paper square elevation={3} sx={{ width: "70%", padding: "2.5%", margin: "auto", mt: "2.5%", display: "flex" }}>
        <SellIcon fontSize='large' sx={{ marginRight: "1.5%" }} />
        <Typography variant="h4" fontWeight={800}>{getTranslation(lang, "my_products")}</Typography>
      </Paper>

      {// @ts-ignore 
        roles[0].name == "SELLER" ?
          (<Paper square elevation={3} sx={{ width: "70%", height: "50%", paddingBottom: "2.5%", pl: "2.5%", pr: "2.5%", margin: "auto", mt: "2.5%", display: "flex", flexDirection: "column", overflowY: "scroll" }}>
            {products.length > 0 && products.map(product => {
              return (<div key={product.objectID} style={{ marginTop: "3.5%" }}><MyProductCard key={product.objectID} objectID={product.objectID} name={product.name} price={product.price} categories={product.categories} description={product.description} /></div>);
            })}
          </Paper>) : (<Typography align='center' marginTop={10}>{getTranslation(lang, "no_rights_to_access")}</Typography>)
      }
      <Copyright />

    </Container>
  );
}

export default ProductsContainer;
import jwt from 'jsonwebtoken';

export function decodeToken(token) {
  try {
    const decoded = jwt.decode(token, { complete: true });
    return decoded;
  } catch (error) {
    console.error('Failed to decode token:', error.message);
    return null;
  }
}